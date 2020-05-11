package com.example.progtech.easierstudentlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.progtech.easierstudentlife.model.MatkulData;
import com.example.progtech.easierstudentlife.model.Todo;
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

public class AddScheduleActivity extends AppCompatActivity {

    MaterialTextView toolbar_title;
    Toolbar toolbar;

    TextInputEditText textInputEditTextDate;
    private String tipe, startTime, endTime, notif, judul, matkul, deskripsi = "";
    Spinner spinner, spinnerNotif, spinnerMatkul;

    ArrayList<MatkulData> mData;
    TextInputEditText txtJam, txtJudul,txtDeskripsi;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);


        toolbar = findViewById(R.id.toolBar);
        toolbar_title = findViewById(R.id.toolBar_title);

        if(toolbar_title!=null && toolbar!=null) {
            toolbar.setTitle("");
            toolbar_title.setText("Add Activity");
            setSupportActionBar(toolbar);
        }

        mData = new ArrayList<>();
        getMatkul();

        setSpinner();

        TextInputLayout tilReminder = findViewById(R.id.addSchedule_til_date);
        textInputEditTextDate = findViewById(R.id.addSchedule_date);

        txtJam = findViewById(R.id.addSchedule_time);

        txtJudul = findViewById(R.id.addSchedule_judul);

        txtDeskripsi = findViewById(R.id.addSchedule_desc);

        // Setup Date picker
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Pilih rentang hari aktif semester");
        final MaterialDatePicker materialDatePicker = builder.build();

        tilReminder.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                Date date = new Date(selection + offsetFromUTC);

                textInputEditTextDate.setText(simpleFormat.format(date));
            }
        });

    }

    private void getMatkul() {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Get "User UID" from Firebase > Authentification > Users.
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(user.getUid()).child("dataMatkul");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        MatkulData s = snapshot.getValue(MatkulData.class);
                        mData.add(s);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Debug",databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

    }

    private void setSpinner() {
        Spinner spinner = findViewById(R.id.addSchedule_spinner_type);

        List<String> tipeAktifitas = new ArrayList<>();
        tipeAktifitas.add(0, "Pilih Jenis Aktifitas");
        tipeAktifitas.add("Aktifitas Mata Kuliah");
        tipeAktifitas.add("Aktifitas Lain - Lain");

        ArrayAdapter<String> taDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,tipeAktifitas);
        taDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(taDataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Pilih Jenis Aktifitas")){
                    tipe = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerNotif = findViewById(R.id.addSchedule_spinner_notif);

        List<String> tipeNotif = new ArrayList<>();
        tipeNotif.add(0, "Pilih Mata Kuliah");
        for(MatkulData m : mData){
            tipeNotif.add(m.getName());
        }

        ArrayAdapter<String> snDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,tipeNotif);
        taDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNotif.setAdapter(snDataAdapter);
        spinnerNotif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Set Notifikasi")){
                    tipe = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMatkul = findViewById(R.id.addSchedule_spinner_matkul);

        List<String> listMatkul = new ArrayList<>();
        listMatkul.add(0, "Set Notifikasi");
        listMatkul.add("30 Menit Sebelum");
        listMatkul.add("45 Menit Sebelum");
        listMatkul.add("1 Jam Sebelum");
        listMatkul.add("2 Jam Sebelum");

        ArrayAdapter<String> smDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,tipeNotif);
        taDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMatkul.setAdapter(smDataAdapter);
        spinnerMatkul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Pilih Jenis Aktifitas")){
                    tipe = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_btn_toolbar) {
            saveData();
            Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveData() {

        tipe = spinner.getSelectedItem().toString();
        String date = textInputEditTextDate.getText().toString().trim();
        String time = txtJam.getText().toString().trim();
        String notif = spinnerNotif.getSelectedItem().toString();
        String judul = txtJudul.getText().toString().trim();
        int matkul = spinnerMatkul.getSelectedItemPosition();


        if(tipe.equalsIgnoreCase("Aktifitas Mata Kuliah")){

        }

    }
}
