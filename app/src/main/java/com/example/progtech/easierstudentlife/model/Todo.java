package com.example.progtech.easierstudentlife.model;

public class Todo  {
    private String title, date, description, time;
    private int prioritas;

    public Todo() {
    }

    public Todo(String title, String date, String description, String time, int prioritas) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.time = time;
        this.prioritas = prioritas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(int prioritas) {
        this.prioritas = prioritas;
    }
}
