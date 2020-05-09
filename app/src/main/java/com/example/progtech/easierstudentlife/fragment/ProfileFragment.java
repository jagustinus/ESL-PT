package com.example.progtech.easierstudentlife.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progtech.easierstudentlife.R;
import com.example.progtech.easierstudentlife.model.UserData;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment<Updated> extends Fragment {
    private TextView usernameView, majorView, univView, taskView, subjectView, activityView, sksView;
    private FirebaseAuth mAuth;
    private ImageView userImageView;
    private FirebaseDatabase database;
    private DatabaseReference reference;

//    UserData userData;
//    public ProfileFragment(UserData user) {
//        userData = user;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        MaterialTextView toolbar_title = getActivity().findViewById(R.id.toolBar_title);
        toolbar_title.setText("Profile");


        return view;
    }
}
