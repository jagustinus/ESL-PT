package com.example.progtech.easierstudentlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progtech.easierstudentlife.model.MatkulData;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMataKuliahActivity extends AppCompatActivity {

    MaterialTextView toolbar_title;
    Toolbar toolbar;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    EditText day, end, name, ruang, start;
    Button savebtn;

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

        day = (EditText) findViewById(R.id.hariEditTxt);
        end = (EditText) findViewById(R.id.jamEndEditTxt);
        name = (EditText) findViewById(R.id.namaEditTxt);
        ruang = (EditText) findViewById(R.id.txtView);
        start = (EditText) findViewById(R.id.jamStartEditTxt);
        savebtn = (Button) findViewById(R.id.hapusMatkulBtn);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaRuang = name.getText().toString();
                String nama = name.getText().toString();
                String room = ruang.getText().toString();
                String awal = start.getText().toString();
                String akhir = end.getText().toString();
                String hari = day.getText().toString();
                MatkulData matkul = new MatkulData(namaRuang,nama, hari, akhir, room, awal);
                if (nama.isEmpty() || room.isEmpty() || awal.isEmpty() || akhir.isEmpty() || hari.isEmpty()) {
                    Toast.makeText(AddMataKuliahActivity.this, "isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(user.getUid()).child("dataMatkul");
                    databaseReference.child(matkul.namaRuang).setValue(matkul);
                    Intent toHome = new Intent(AddMataKuliahActivity.this, HomeActivity.class);
                    startActivity(toHome);
                    finish();
                }


            }
        });
    }


}
