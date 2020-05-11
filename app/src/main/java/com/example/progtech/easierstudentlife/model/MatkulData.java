package com.example.progtech.easierstudentlife.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MatkulData implements Parcelable {
    public String day,end,name,start,namaRuang;
    public String ruang;

    List<Todo> todos;

    public MatkulData() {
    }

    public MatkulData(String day, String end, String name, String start, String namaRuang, String ruang) {
        this.day = day;
        this.end = end;
        this.name = name;
        this.start = start;
        this.namaRuang = namaRuang;
        this.ruang = ruang;
        this.todos = new ArrayList<>();
    }

    public MatkulData(String day, String end, String name, String start, String namaRuang, String ruang, List<Todo> todos) {
        this.day = day;
        this.end = end;
        this.name = name;
        this.start = start;
        this.namaRuang = namaRuang;
        this.ruang = ruang;
        this.todos = todos;
    }

    protected MatkulData(Parcel in) {
        day = in.readString();
        end = in.readString();
        name = in.readString();
        start = in.readString();
        namaRuang = in.readString();
        ruang = in.readString();
    }

    public static final Creator<MatkulData> CREATOR = new Creator<MatkulData>() {
        @Override
        public MatkulData createFromParcel(Parcel in) {
            return new MatkulData(in);
        }

        @Override
        public MatkulData[] newArray(int size) {
            return new MatkulData[size];
        }
    };

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(day);
        dest.writeString(end);
        dest.writeString(name);
        dest.writeString(start);
        dest.writeString(namaRuang);
        dest.writeString(ruang);
    }
}
