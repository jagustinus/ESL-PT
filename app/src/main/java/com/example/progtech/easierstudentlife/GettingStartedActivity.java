package com.example.progtech.easierstudentlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class GettingStartedActivity extends AppCompatActivity {

    MaterialTextView toolbar_title;
    Toolbar toolbar;

    String semester, start, end;

    TextInputEditText textInputEditTextStart, textInputEditTextEnd;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        toolbar = findViewById(R.id.toolBar);
        toolbar_title = findViewById(R.id.toolBar_title);


        if(toolbar_title!=null && toolbar!=null) {
            toolbar.setTitle("");
            toolbar_title.setText("Setting Awal");
            setSupportActionBar(toolbar);
        }

        TextInputLayout textInputLayoutStart = findViewById(R.id.getting_started_til_start);

        textInputEditTextStart = findViewById(R.id.getting_started_et_start);
        textInputEditTextEnd = findViewById(R.id.getting_started_et_end);

        setSpinnerSemester();

        // Setup Date picker
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Pilih rentang hari aktif semester");
        final MaterialDatePicker materialDatePicker = builder.build();

        textInputLayoutStart.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long,Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long,Long> selection) {
                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                Date date = new Date(selection.first + offsetFromUTC);
                Date date1 = new Date(selection.second + offsetFromUTC);

                textInputEditTextStart.setText(simpleFormat.format(date));
                textInputEditTextEnd.setText(simpleFormat.format(date1));
                start = simpleFormat.format(date);
                end = simpleFormat.format(date1);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }




        Button btnSave = findViewById(R.id.getting_started_btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Semester smst = new Semester(semester, start,end);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                // Get "User UID" from Firebase > Authentification > Users.
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(user.getUid()).child("semester");
                databaseReference.child(smst.name).setValue(smst);

                // TODO : Change data on sharedprefrence so this activity only open once after user registered.
                Intent intent = new Intent(GettingStartedActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private static class Semester{
        String name;
        String end, start;

        public Semester(String name, String end, String start) {
            this.name = name;
            this.end = end;
            this.start = start;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }
    }

    private void setSpinnerSemester() {
        Spinner spinner = findViewById(R.id.getting_started_spinner_semester);

        List<String> tipeAktifitas = new ArrayList<>();
        tipeAktifitas.add(0, "Pilih semester aktif");
        tipeAktifitas.add("Semester 1");
        tipeAktifitas.add("Semester 2");
        tipeAktifitas.add("Semester 3");
        tipeAktifitas.add("Semester 4");
        tipeAktifitas.add("Semester 5");
        tipeAktifitas.add("Semester 6");
        tipeAktifitas.add("Semester 7");
        tipeAktifitas.add("Semester 8");
        tipeAktifitas.add("Semester 9");
        tipeAktifitas.add("Semester 10");

        ArrayAdapter<String> taDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,tipeAktifitas);
        taDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(taDataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Pilih Jenis Aktifitas")){
                    semester = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
