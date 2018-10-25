package com.example.team32gb.jobit.Model.ListJobSearch;

public class DataJob {
    private String nameJob;
    private String nameCompany;
    private String time;

    public DataJob() {
    }

    public DataJob(String nameJob, String nameCompany, String time) {
        this.nameJob = nameJob;
        this.nameCompany = nameCompany;
        this.time = time;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
