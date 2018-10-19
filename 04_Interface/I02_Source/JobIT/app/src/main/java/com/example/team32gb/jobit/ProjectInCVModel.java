package com.example.team32gb.jobit;

public class ProjectInCVModel {
    private String name;
    private String decription;
    private String role;
    private long numberMember;

    public ProjectInCVModel(String name, String decription, String role, long numberMember) {
        this.name = name;
        this.decription = decription;
        this.role = role;
        this.numberMember = numberMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getNumberMember() {
        return numberMember;
    }

    public void setNumberMember(long numberMember) {
        this.numberMember = numberMember;
    }
}
