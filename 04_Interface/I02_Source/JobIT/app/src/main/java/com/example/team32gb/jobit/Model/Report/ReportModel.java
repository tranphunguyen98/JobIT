package com.example.team32gb.jobit.Model.Report;

public class ReportModel {
    protected String idReporter; //id của người tố cáo
    protected String idAccused; //id của người bị tố cáo
    protected String decription;
    protected String adminComment;  //Nếu adminComment=="" thì tố cáo này chưa được phê duyệt


    public ReportModel() {
        adminComment="";
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
}
