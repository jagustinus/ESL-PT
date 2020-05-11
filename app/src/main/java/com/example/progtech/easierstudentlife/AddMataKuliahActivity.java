package com.example.progtech.easierstudentlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.progtech.easierstudentlife.model.MatkulData;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddMataKuliahActivity extends AppCompatActivity {

    MaterialTextView toolbar_title;
    Toolbar toolbar;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    EditText end, name, ruang, start,namaKelas;
    Button savebtn;
    String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mata_kuliah);

        toolbar = findViewById(R.id.toolBar);
        toolbar_title = findViewById(R.id.toolBar_title);

        if (toolbar_title != null && toolbar != null) {
            toolbar.setTitle("");
            toolbar_title.setText("Add Mata Kuliah");
            setSupportActionBar(toolbar);
        }

        end = (EditText) findViewById(R.id.jamEndEditTxt);
        name = (EditText) findViewById(R.id.namaEditTxt);
        ruang = (EditText) findViewById(R.id.txtView);
        start = (EditText) findViewById(R.id.jamStartEditTxt);
        namaKelas = (EditText) findViewById(R.id.namaKelasEditTxt);
        savebtn = (Button) findViewById(R.id.hapusMatkulBtn);

        setSpinnerHari();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaRuang = namaKelas.getText().toString();
                String nama = name.getText().toString();
                String room = ruang.getText().toString();
                String awal = start.getText().toString();
                String akhir = end.getText().toString();
                MatkulData matkul = new MatkulData(namaRuang,nama, day, akhir, room, awal);
                if (nama.isEmpty() || room.isEmpty() || awal.isEmpty() || akhir.isEmpty() || day.isEmpty()) {
                    Toast.makeText(AddMataKuliahActivity.this, "isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(user.getUid()).child("dataMatkul");
                    databaseReference.child(matkul.name).setValue(matkul);
                    Intent toHome = new Intent(AddMataKuliahActivity.this, HomeActivity.class);
                    startActivity(toHome);
                    finish();
                }


            }
        });
    }
    private void setSpinnerHari() {
        Spinner spinner = findViewById(R.id.hariSpinner);

        List<String> tipeAktifitas = new ArrayList<>();
        tipeAktifitas.add(0, "Set Day");
        tipeAktifitas.add("Monday");
        tipeAktifitas.add("Tuesday");
        tipeAktifitas.add("Wednesday");
        tipeAktifitas.add("Thursday");
        tipeAktifitas.add("Friday");
        tipeAktifitas.add("Saturday");
        tipeAktifitas.add("Sunday");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,tipeAktifitas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).equals("Pilih Jenis Aktifitas")){
                    day = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
