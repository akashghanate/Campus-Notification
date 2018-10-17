package com.example.abhinandan.onlinedb.models;



public class cardview_details {
    int background;
    String teacher_name;
    String date;
    String time;

    public cardview_details(int background,String teacher_name,String date,String time) {
        this.background=background;
        this.teacher_name=teacher_name;
        this.date=date;
        this.time=time;
    }

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

    public int getBackground() {
        return background;
    }


    public void setBackground(int background) {
        this.background = background;
    }
}
