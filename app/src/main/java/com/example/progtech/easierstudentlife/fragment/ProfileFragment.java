package com.example.progtech.easierstudentlife.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.progtech.easierstudentlife.R;
import com.example.progtech.easierstudentlife.model.UserData;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    UserData userData;
    public ProfileFragment(UserData user) {
        userData = user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //coding ndek sini

        return view;
    }
}
