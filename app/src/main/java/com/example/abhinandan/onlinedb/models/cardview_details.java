package com.example.abhinandan.onlinedb.models;


import android.graphics.Bitmap;

public class cardview_details {
    Bitmap background;
    String teacher_name;
    String date;
    String time;


    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getBackground() {
        return background;
    }


    public void setBackground(Bitmap background) {
        this.background = background;
    }
}
