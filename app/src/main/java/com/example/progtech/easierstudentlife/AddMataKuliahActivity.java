package com.example.progtech.easierstudentlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMataKuliahActivity extends AppCompatActivity {

    MaterialTextView toolbar_title;
    Toolbar toolbar;

    EditText hari,akhir,nama,room,awal;
    Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mata_kuliah);

        toolbar = findViewById(R.id.toolBar);
        toolbar_title = findViewById(R.id.toolBar_title);

        if(toolbar_title!=null && toolbar!=null) {
            toolbar.setTitle("");
            toolbar_title.setText("Add Mata Kuliah");
            setSupportActionBar(toolbar);
        }

        hari= (EditText) findViewById(R.id.hariEditTxt);
        akhir= (EditText) findViewById(R.id.jamEndEditTxt);
        nama= (EditText) findViewById(R.id.namaEditTxt);
        room= (EditText) findViewById(R.id.ruangEditTxt);
        awal= (EditText) findViewById(R.id.jamStartEditTxt);
        savebtn=(Button) findViewById(R.id.simpanMatkulBtn);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= nama.getText().toString();
                String ruang=room.getText().toString();
                String start=awal.getText().toString();
                String end=akhir.getText().toString();
                String day=hari.getText().toString();
                if (name.isEmpty() || ruang.isEmpty() || start.isEmpty() || end.isEmpty() || day.isEmpty()){
                    Toast.makeText(AddMataKuliahActivity.this, "isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseDatabase.getInstance().getReference().child("matkulData").push().child("name").setValue(name);
                    FirebaseDatabase.getInstance().getReference().child("matkulData").push().child("ruang").setValue(ruang);
                    FirebaseDatabase.getInstance().getReference().child("matkulData").push().child("start").setValue(start);
                    FirebaseDatabase.getInstance().getReference().child("matkulData").push().child("end").setValue(end);
                    FirebaseDatabase.getInstance().getReference().child("matkulData").push().child("day").setValue(day);
                }

            }
        });
    }


}
