package com.example.team32gb.jobit;

import java.util.ArrayList;

public class CVEmployeeModel {
    String nameUser;
    String dateOfBird;
    boolean isMale;
    String email;
    String phoneNumber;
    String address;
    boolean isSingle;
    String hobbies;

    String careerObjective;
    String eduQuali;
    String workExperience;
    String skill;
    String language;

    ArrayList<ProjectInCVModel> projects = new ArrayList<>();
    public final static int MAX_PROJECT_IN_CV =2;

    public CVEmployeeModel() {
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getDateOfBird() {
        return dateOfBird;
    }

    public void setDateOfBird(String dateOfBird) {
        this.dateOfBird = dateOfBird;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getCareerObjective() {
        return careerObjective;
    }

    public void setCareerObjective(String careerObjective) {
        this.careerObjective = careerObjective;
    }

    public String getEduQuali() {
        return eduQuali;
    }

    public void setEduQuali(String eduQuali) {
        this.eduQuali = eduQuali;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<ProjectInCVModel> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<ProjectInCVModel> projects) {
        this.projects = projects;
    }
}
