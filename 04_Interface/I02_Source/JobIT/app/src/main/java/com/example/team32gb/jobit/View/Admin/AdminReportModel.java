package com.example.team32gb.jobit.View.Admin;

public class AdminReportModel {
    String idReporter;  //Người tố cáo
    String idAccused;   //Người bị tố cáo

    String nameReporter;
    String nameAccused;
    String decription;
    String dateTime;
    Boolean isEmployee;    //Cho biết bị cáo là người tìm việc hay nhà tuyển dụng

    public AdminReportModel() {
    }

    public AdminReportModel(String idReporter, String idAccused, String nameReporter, String nameAccused, String decription, String dateTime, Boolean isEmployee) {
        this.idReporter = idReporter;
        this.idAccused = idAccused;
        this.nameReporter = nameReporter;
        this.nameAccused = nameAccused;
        this.decription = decription;
        this.dateTime = dateTime;
        this.isEmployee = isEmployee;
    }

    public String getIdReporter() {
        return idReporter;
    }

    public void setIdReporter(String idReporter) {
        this.idReporter = idReporter;
    }

    public String getIdAccused() {
        return idAccused;
    }

    public void setIdAccused(String idAccused) {
        this.idAccused = idAccused;
    }

    public String getNameReporter() {
        return nameReporter;
    }

    public void setNameReporter(String nameReporter) {
        this.nameReporter = nameReporter;
    }

    public String getNameAccused() {
        return nameAccused;
    }

    public void setNameAccused(String nameAccused) {
        this.nameAccused = nameAccused;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }
}
