package com.example.progtech.easierstudentlife.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.progtech.easierstudentlife.R;
import com.example.progtech.easierstudentlife.adapter.MatkulAdapter;
import com.example.progtech.easierstudentlife.holder.MatkulHolder;
import com.example.progtech.easierstudentlife.model.MatkulData;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<MatkulData> m = new ArrayList<>();
    MatkulAdapter matkulAdapter;

    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        MaterialTextView toolbar_title = getActivity().findViewById(R.id.toolBar_title);
        toolbar_title.setText("Learning");

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData").child("dataMatkul");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    MatkulData matkulData = dataSnapshot1.getValue(MatkulData.class);
                    m.add(matkulData);
                }
                matkulAdapter = new MatkulAdapter(getActivity(),m);
                recyclerView.setAdapter(matkulAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
