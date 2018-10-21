package com.example.team32gb.jobit.Model.CreateCV;

public class ProjectInCVModel {
    private String name;
    private String decription;
    private String role;
    private Long numberMember;

    public ProjectInCVModel() {
        name = "";
        decription = "";
        role = "";
        numberMember = new Long(0);;
    }

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

    public Long getNumberMember() {
        return numberMember;
    }

    public void setNumberMember(Long numberMember) {
        this.numberMember = numberMember;
    }
}
