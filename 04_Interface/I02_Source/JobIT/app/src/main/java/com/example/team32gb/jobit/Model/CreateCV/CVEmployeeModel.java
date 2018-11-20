package com.example.team32gb.jobit.Model.CreateCV;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CVEmployeeModel {
    String nameUser;
    String dateOfBird;
    Boolean isMale;
    String email;
    String phoneNumber;
    String address;
    Boolean isSingle;
    String hobbies;

    String careerObjective;
    String eduQuali;
    String workExperience;
    String skill;
    String language;

    List<ProjectInCVModel> projects;
    public CVEmployeeModel() {
        nameUser = "";
        dateOfBird = "";
        isMale = false;
        email = "";
        phoneNumber = "";
        address = "";
        isSingle = false;
        hobbies = "";

        careerObjective = "";
        eduQuali = "";
        workExperience = "";
        skill = "";
        language = "";
        projects = new ArrayList<>();
    }

    public List<ProjectInCVModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectInCVModel> projects) {
        this.projects = projects;
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

    public Boolean getIsMale() {
        return isMale;
    }

    public void setIsMale(Boolean male) {
        this.isMale = male;
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

    public Boolean getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Boolean single) {
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

    public final static int MAX_PROJECT_IN_CV = 2;


}

