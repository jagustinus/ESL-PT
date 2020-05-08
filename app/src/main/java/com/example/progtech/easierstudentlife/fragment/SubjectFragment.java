package com.example.progtech.easierstudentlife.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.progtech.easierstudentlife.R;
import com.google.android.material.textview.MaterialTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment {

    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        MaterialTextView toolbar_title = getActivity().findViewById(R.id.toolBar_title);
        toolbar_title.setText("Subject");

        return view;
    }
}
