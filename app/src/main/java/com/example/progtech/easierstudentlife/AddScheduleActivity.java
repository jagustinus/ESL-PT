package com.example.progtech.easierstudentlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class AddScheduleActivity extends AppCompatActivity {

    MaterialTextView toolbar_title;
    Toolbar toolbar;

    private String tipe, startTime, endTime, notif, judul, matkul, deskripsi = "";

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

        setSpinner();




    }

    private void setSpinner() {
        Spinner spinner = findViewById(R.id.addSchecule_spinner_type);

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
            Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
