package com.example.progtech.easierstudentlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.progtech.easierstudentlife.model.MatkulData;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;
import com.google.android.material.textview.MaterialTextView;
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver;
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager;
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendar;
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter;
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarEventActivity extends AppCompatActivity {

    MaterialTextView toolbar_title;
    Toolbar toolbar;

    ArrayList<MatkulData> allMatkul;
    TimetableView timetableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        allMatkul = (ArrayList<MatkulData>) args.getSerializable("MATKULLIST");

        timetableView = findViewById(R.id.timetable);

        toolbar = findViewById(R.id.toolBar);
        toolbar_title = findViewById(R.id.toolBar_title);

        if(toolbar_title!=null && toolbar!=null) {
            toolbar.setTitle("");
            toolbar_title.setText("Calendar View");
            setSupportActionBar(toolbar);
        }


        setSchedule();


    }

    private void setSchedule() {
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        for (MatkulData m : allMatkul){
            Schedule schedule = new Schedule();
            schedule.setClassTitle(m.getName()); // sets subject
            schedule.setClassPlace(m.namaRuang + " | " + m.ruang); // sets place
            schedule.setProfessorName(m.getDay() + " | " +m.getStart() + " - " +m.getEnd()); // sets professor
            int x = 0;
            switch (m.day){
                case "Monday":
                    x = 0;
                    break;
                case "Tuesday":
                    x = 1;
                    break;
                case "Wednesday":
                    x = 2;
                    break;
                case "Thursday":
                    x = 3;
                    break;
                case "Friday":
                    x = 4;
                    break;
                case "Saturday":
                    x = 5;
                    break;
                case "Sunday":
                    x = 6;
                    break;
            }
            schedule.setDay(x);
            schedule.setStartTime(new Time(Integer.parseInt(m.getStart().substring(0,2)),Integer.parseInt(m.getStart().substring(3,5)))); // sets the beginning of class time (hour,minute)
            schedule.setEndTime(new Time(Integer.parseInt(m.getEnd().substring(0,2)),Integer.parseInt(m.getEnd().substring(3,5)))); // sets the end of class time (hour,minute)
            schedules.add(schedule);
//.. add one or more schedules

            timetableView.add(schedules);

        }


//        MatkulData m = allMatkul.get(0);
//        Toast.makeText(this, "Jam : " + m.getStart().substring(0,2) + ", Menit : "+m.getStart().substring(3,5), Toast.LENGTH_LONG).show();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // yourdate is an object of type Date
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        timetableView.edit(dayOfWeek,schedules);
    }
}
