package com.example.team32gb.jobit.Model.PostJob;

public class ItemPostJob extends DataPostJob {
    String idJob;
    String idCompany;

    public ItemPostJob(DataPostJob dataPostJob) {
        this.setNameCompany(dataPostJob.getNameCompany());
        this.setNameJob(dataPostJob.getNameJob());
        this.setTypeJob(dataPostJob.getTypeJob());
        this.setMaxSalary(dataPostJob.getMaxSalary());
        this.setMinSalary(dataPostJob.getMinSalary());
        this.setNumberEmployer(dataPostJob.getNumberEmployer());
        this.setDescription(dataPostJob.getDescription());
        this.setQualification(dataPostJob.getQualification());
        this.setTime(dataPostJob.getTime());
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
