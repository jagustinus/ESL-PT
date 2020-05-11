package com.example.progtech.easierstudentlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.progtech.easierstudentlife.fragment.SubjectFragment;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMataKuliahActivity extends AppCompatActivity {

    MaterialTextView toolbar_title;
    Toolbar toolbar;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    EditText hari, akhir, nama, room, awal;
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

        hari = (EditText) findViewById(R.id.hariEditTxt);
        akhir = (EditText) findViewById(R.id.jamEndEditTxt);
        nama = (EditText) findViewById(R.id.namaEditTxt);
        room = (EditText) findViewById(R.id.ruangEditTxt);
        awal = (EditText) findViewById(R.id.jamStartEditTxt);
        savebtn = (Button) findViewById(R.id.simpanMatkulBtn);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nama.getText().toString();
                String ruang = room.getText().toString();
                String start = awal.getText().toString();
                String end = akhir.getText().toString();
                String day = hari.getText().toString();
                Matakuliah matkul = new Matakuliah(name, day, end, ruang, start);
                if (name.isEmpty() || ruang.isEmpty() || start.isEmpty() || end.isEmpty() || day.isEmpty()) {
                    Toast.makeText(AddMataKuliahActivity.this, "isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child(user.getUid()).child("dataMatkul");
                    databaseReference.child(matkul.namaMatkul).setValue(name);
                    databaseReference.child(matkul.namaMatkul).child(matkul.room).setValue(ruang);
                    databaseReference.child(matkul.namaMatkul).child(matkul.awal).setValue(start);
                    databaseReference.child(matkul.namaMatkul).child(matkul.akhir).setValue(end);
                    databaseReference.child(matkul.namaMatkul).child(matkul.hari).setValue(day);
                    databaseReference.child(matkul.namaMatkul).child(matkul.namaMatkul).setValue(name);
                    Intent toHome = new Intent(AddMataKuliahActivity.this, HomeActivity.class);
                    startActivity(toHome);
                    finish();
                }


            }
        });
    }

    private static class Matakuliah {
        String namaMatkul;
        String nama,hari, akhir, room, awal;

        public Matakuliah(String name, String hari, String akhir, String room, String awal) {
            this.namaMatkul = name;
            this.hari = hari;
            this.akhir = akhir;
            this.room = room;
            this.awal = awal;
        }

        public String getNamaMatkul() {
            return namaMatkul;
        }

        public void setNamaMatkul(String namaMatkul) {
            this.namaMatkul = namaMatkul;
        }

        public String getHari() {
            return hari;
        }

        public void setHari(String hari) {
            this.hari = hari;
        }

        public String getAkhir() {
            return akhir;
        }

        public void setAkhir(String akhir) {
            this.akhir = akhir;
        }

        public String getRoom() {
            return room;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public String getAwal() {
            return awal;
        }

        public void setAwal(String awal) {
            this.awal = awal;
        }
    }
}
