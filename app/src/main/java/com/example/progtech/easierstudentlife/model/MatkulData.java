package com.example.progtech.easierstudentlife.model;

public class MatkulData {
    public String day,end,name,start,semester,namaRuang;
    public int ruang;

    public MatkulData() {
    }

    public MatkulData(String day, String end, String name, int ruang, String start,String namaRuang) {
        this.day = day;
        this.end = end;
        this.name = name;
        this.ruang = ruang;
        this.start = start;
        this.semester = semester;
        this.namaRuang = namaRuang;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

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

    public int getRuang() {
        return ruang;
    }

    public void setRuang(int ruang) {
        this.ruang = ruang;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
