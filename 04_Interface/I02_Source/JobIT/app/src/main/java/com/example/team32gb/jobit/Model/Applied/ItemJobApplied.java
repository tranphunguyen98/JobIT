package com.example.team32gb.jobit.Model.Applied;

import com.example.team32gb.jobit.Model.ListJobSearch.DataJob;

public class ItemJobApplied extends DataJob {
    String idJob;
    String idCompany;
    String timeApply;
    public ItemJobApplied() {
    }

    public String getTimeApply() {
        return timeApply;
    }

    public void setTimeApply(String timeApply) {
        this.timeApply = timeApply;
    }

    public ItemJobApplied(DataJob dataJob) {
        this.setNameCompany(dataJob.getNameCompany());
        this.setNameJob(dataJob.getNameJob());
        this.setTime(dataJob.getNameCompany());

    }
    public void setDataJob(DataJob dataJob) {
        this.setNameCompany(dataJob.getNameCompany());
        this.setNameJob(dataJob.getNameJob());
        this.setTime(dataJob.getNameCompany());
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
