package com.example.progtech.easierstudentlife.model;

public class MatkulData {
    public String day,end,name,start,semester,namaRuang;
    public String ruang;

    public MatkulData() {
    }

    public MatkulData(String namaRuang, String name, String day, String end, String ruang,String start) {
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

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
