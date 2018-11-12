package com.example.team32gb.jobit;

import android.widget.TextView;

public class DataApplied {
    private String Name;
    private String DayApplied;
    private String idJobSeeker;
    private String idCompany;
    private String idJob;

    public DataApplied() {
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
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

    public String getIdJobSeeker() {
        return idJobSeeker;
    }

    public void setIdJobSeeker(String idJobSeeker) {
        this.idJobSeeker = idJobSeeker;
    }
}
