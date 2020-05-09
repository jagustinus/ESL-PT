package com.example.progtech.easierstudentlife.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progtech.easierstudentlife.R;

public class MatkulHolder extends RecyclerView.ViewHolder {

    public TextView namaMatkul,dayTime,semester;

    public MatkulHolder(@NonNull View itemView) {
        super(itemView);

        this.namaMatkul = itemView.findViewById(R.id.namaMatkulTxtView);
        this.dayTime = itemView.findViewById(R.id.DayTimeTxtView);
        this.semester = itemView.findViewById(R.id.semesterTxtView);

    }
}
