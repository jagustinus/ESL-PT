package com.example.progtech.easierstudentlife.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progtech.easierstudentlife.AddScheduleActivity;
import com.example.progtech.easierstudentlife.CalendarEventActivity;
import com.example.progtech.easierstudentlife.GettingStartedActivity;
import com.example.progtech.easierstudentlife.LoginActivity;
import com.example.progtech.easierstudentlife.R;
import com.example.progtech.easierstudentlife.adapter.MatkulAdapter;
import com.example.progtech.easierstudentlife.model.MatkulData;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import kotlin.TuplesKt;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    String newDate;
    MaterialTextView materialTextViewDate;
    MaterialDatePicker materialDatePicker;
    TimetableView ttv;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private String currentDateandTime;
    int dayOfWeek;

    RecyclerView recyclerView;
    ArrayList<MatkulData> m;
    MatkulAdapter matkulAdapter;

    View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        MaterialTextView toolbar_title = getActivity().findViewById(R.id.toolBar_title);
        toolbar_title.setText("Easier Student Life");
        Toolbar toolbar = getActivity().findViewById(R.id.toolBar);
        setHasOptionsMenu(true);

        recyclerView  = view.findViewById(R.id.rv_event_home);


        FloatingActionButton fabAdd = view.findViewById(R.id.home_fab_add_btn);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), AddScheduleActivity.class);
                Intent i = new Intent(getActivity(), GettingStartedActivity.class);
                startActivity(i);
            }
        });

        setTodayDate();
        showSchedule(Calendar.getInstance().getTime());


        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Pilih tanggal");
        materialDatePicker = builder.build();

        materialTextViewDate = view.findViewById(R.id.home_fragment_date_text);
        materialTextViewDate.setText(currentDateandTime.toString());
        Log.d("debug : ", currentDateandTime.toString());
        materialTextViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setup Date picker
                materialDatePicker.show(getChildFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime());

                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                Date date = new Date(selection + offsetFromUTC);

                materialTextViewDate.setText(simpleFormat.format(date));
                newDate = simpleFormat.format(date);
                showSchedule(date);
            }
        });

        return view;
    }

    private void setTodayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-mm-yyyy", Locale.getDefault());
        currentDateandTime = simpleDateFormat.format(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // yourdate is an object of type Date
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK); // this will for example return 3 for tuesday
    }

    private void showSchedule(Date date) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Get "User UID" from Firebase > Authentification > Users.
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(user.getUid()).child("dataMatkul");

        Calendar calendar = Calendar.getInstance();
        final Date today = calendar.getTime();


        recyclerView = (RecyclerView) view.findViewById(R.id.rv_event_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        m = new ArrayList<>();
        matkulAdapter = new MatkulAdapter(getActivity(),m);
        recyclerView.setAdapter(matkulAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    m.clear();
                    matkulAdapter.notifyDataSetChanged();

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        MatkulData s = snapshot.getValue(MatkulData.class);

                        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                        String todayName = outFormat.format(today);
                        if(s.getDay().equalsIgnoreCase(todayName)){
                            m.add(s);
                            Toast.makeText(getActivity(), s.getDay(), Toast.LENGTH_LONG).show();
                        }
                        matkulAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Debug",databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.timetable_menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_btn_toolbar) {
            Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getActivity(), CalendarEventActivity.class);
            startActivity(intent);

//            LayoutInflater inflater, ViewGroup container,
//                    Bundle savedInstanceState

            return true;
        }


        return super.onOptionsItemSelected(item);

    }

    private String dayToHari(String dayName){
        final String MONDAY = "monday";
        final String TUESDAY = "tuesday";
        final String WEDNESDAY = "wednesday";
        final String THURSDAY = "thursday";
        final String FRIDAY = "friday";
        final String SATURDAY = "saturday";
        final String SUNDAY = "sunday";

        if(dayName.equalsIgnoreCase(MONDAY))
            return "Senin";
        else if (dayName.equalsIgnoreCase(TUESDAY))
            return "Selasa";
        else if (dayName.equalsIgnoreCase(WEDNESDAY))
            return "Rabu";
        else if (dayName.equalsIgnoreCase(THURSDAY))
            return "Kamis";
        else if (dayName.equalsIgnoreCase(FRIDAY))
            return "Jumat";
        else if (dayName.equalsIgnoreCase(SATURDAY))
            return "Sabtu";
        else if (dayName.equalsIgnoreCase(SUNDAY))
            return "Minggu";

        return dayName;
    }
}
