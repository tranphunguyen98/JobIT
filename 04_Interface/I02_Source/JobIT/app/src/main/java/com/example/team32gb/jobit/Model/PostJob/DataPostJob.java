package com.example.team32gb.jobit.Model.PostJob;

public class DataPostJob {
    String nameCompany;
    String nameJob;
    String typeJob;
    String each;
    String maxSalary;
    String minSalary;
    String numberEmployer;
    String description;
    String qualification;
    String time;

    public DataPostJob() {
    }

    public String getEach() {
        return each;
    }

    public void setEach(String each) {
        this.each = each;
    }

    public DataPostJob(String nameCompany, String nameJob, String typeJob, String each, String maxSalary, String minSalary, String numberEmployer, String description, String qualification, String time) {
        this.nameCompany = nameCompany;
        this.nameJob = nameJob;
        this.typeJob = typeJob;
        this.each = each;
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.numberEmployer = numberEmployer;
        this.description = description;
        this.qualification = qualification;
        this.time = time;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public String getTypeJob() {
        return typeJob;
    }

    public void setTypeJob(String typeJob) {
        this.typeJob = typeJob;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getNumberEmployer() {
        return numberEmployer;
    }

    public void setNumberEmployer(String numberEmployer) {
        this.numberEmployer = numberEmployer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

