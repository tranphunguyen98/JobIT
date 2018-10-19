package com.example.team32gb.jobit;

import android.widget.TextView;

public class DataApplied {
    private String Name;
    private String DayApplied;

    public DataApplied() {
    }

    public DataApplied(String name, String dayApplied) {
        Name = name;
        DayApplied = dayApplied;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDayApplied() {
        return DayApplied;
    }

    public void setDayApplied(String dayApplied) {
        DayApplied = dayApplied;
    }
}
