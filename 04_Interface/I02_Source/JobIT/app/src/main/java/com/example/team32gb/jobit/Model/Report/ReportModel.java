package com.example.team32gb.jobit.Model.Report;

public class ReportModel {
    String idReporter; //id của người tố cáo
    String idAccused; //id của người bị tố cáo
    String decription;
    String adminComment;
    String dateSendReport;
    Boolean isWarned;



    public ReportModel() {
        isWarned = false;   //Chưa được phê duyệt
        adminComment ="";
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

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public Boolean getisWarned() {
        return isWarned;
    }

    public void setisWarned(Boolean warned) {
        isWarned = warned;
    }

    public String getDateSendReport() {
        return dateSendReport;
    }

    public void setDateSendReport(String dateSendReport) {
        this.dateSendReport = dateSendReport;
    }
}
