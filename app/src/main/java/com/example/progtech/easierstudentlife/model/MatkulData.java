package com.example.progtech.easierstudentlife.model;

public class MatkulData {
    public String namaMatkul,dayTime,classroom,semester;

    public MatkulData() {
    }

    public MatkulData(String namaMatkul, String dayTime, String classroom, String semester) {
        this.namaMatkul = namaMatkul;
        this.dayTime = dayTime;
        this.classroom = classroom;
        this.semester = semester;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
