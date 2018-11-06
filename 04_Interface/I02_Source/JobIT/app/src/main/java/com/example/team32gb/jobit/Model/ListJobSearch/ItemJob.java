package com.example.team32gb.jobit.Model.ListJobSearch;

public class ItemJob extends DataJob{
    String idJob;
    String idCompany;
    public ItemJob () {
    }
    public ItemJob(DataJob dataJob) {
        this.setNameCompany(dataJob.getNameCompany());
        this.setNameJob(dataJob.getNameJob());
        this.setTime(dataJob.getTime());

    }
    public String getIdJob() {
        return idJob;
    }

    public void setIdJob(String idJob) {
        this.idJob = idJob;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }
}
