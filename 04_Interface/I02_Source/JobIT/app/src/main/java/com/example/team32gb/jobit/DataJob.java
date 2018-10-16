package com.example.team32gb.jobit;

public class DataJob {
    private String NameJob;
    private String NameCompany;
    private String Time;

    public DataJob() {
    }

    public DataJob(String nameJob, String nameCompany, String time) {
        NameJob = nameJob;
        NameCompany = nameCompany;
        Time = time;
    }

    public String getNameJob() {
        return NameJob;
    }

    public void setNameJob(String nameJob) {
        NameJob = nameJob;
    }

    public String getNameCompany() {
        return NameCompany;
    }

    public void setNameCompany(String nameCompany) {
        NameCompany = nameCompany;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
