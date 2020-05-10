package com.example.progtech.easierstudentlife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progtech.easierstudentlife.R;
import com.example.progtech.easierstudentlife.holder.MatkulHolder;
import com.example.progtech.easierstudentlife.model.MatkulData;
import com.example.progtech.easierstudentlife.model.UserData;

import java.util.ArrayList;

public class MatkulAdapter extends RecyclerView.Adapter<MatkulHolder> {
    Context c;
    ArrayList<MatkulData> matkulData;

    public MatkulAdapter(Context c, ArrayList<MatkulData> matkulData) {
        this.c = c;
        this.matkulData = matkulData;
    }

    @NonNull
    @Override
    public MatkulHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mata_kuliah_cardview,parent, false);
        return new MatkulHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatkulHolder holder, int position) {

        holder.namaMatkul.setText(matkulData.get(position).getName());
        holder.dayTime.setText(matkulData.get(position).getDay()+" | "+" "+matkulData.get(position).getStart()+" - "+matkulData
        .get(position).getEnd());
        holder.semester.setText("at "+matkulData.get(position).getNamaRuang()+"("+matkulData.get(position).getRuang()+")");

    }

    @Override
    public int getItemCount() {
        return matkulData.size();
    }
}
