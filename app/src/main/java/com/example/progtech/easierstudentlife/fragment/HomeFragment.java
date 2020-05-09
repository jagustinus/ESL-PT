package com.example.progtech.easierstudentlife.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.progtech.easierstudentlife.AddScheduleActivity;
import com.example.progtech.easierstudentlife.R;
import com.github.tlaabs.timetableview.Schedule;
import com.github.tlaabs.timetableview.Time;
import com.github.tlaabs.timetableview.TimetableView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MaterialTextView toolbar_title = getActivity().findViewById(R.id.toolBar_title);
        toolbar_title.setText("Easier Student Life");

        TimetableView timetableView = view.findViewById(R.id.timetable);
        FloatingActionButton fabAdd = view.findViewById(R.id.home_fab_add_btn);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddScheduleActivity.class);
                startActivity(i);
            }
        });



        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        Schedule schedule = new Schedule();
        schedule.setClassTitle("Data Structure"); // sets subject
        schedule.setClassPlace("IT-601"); // sets place
        schedule.setProfessorName("Won Kim"); // sets professor
        schedule.setStartTime(new Time(10,0)); // sets the beginning of class time (hour,minute)
        schedule.setEndTime(new Time(13,30)); // sets the end of class time (hour,minute)
        schedules.add(schedule);

        timetableView.add(schedules);


        return view;
    }
}
