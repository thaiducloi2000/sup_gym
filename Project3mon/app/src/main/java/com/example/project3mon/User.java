package com.example.project3mon;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {


    private String name;
    private String position;
    private String description;
    private String image;
    private Date birthday;
    private String phoneNumber;
    private String gender;
    private String confirmInfo;
    private String email;
    private int roleID;

    public User(String name, String position, String description, String image, Date birthday, String phoneNumber, String gender, String confirmInfo, String email, int roleID) {
        this.name = name;
        this.position = position;
        this.description = description;
        this.image = image;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.confirmInfo = confirmInfo;
        this.email = email;
        this.roleID = roleID;
    }

    public User(String name, String position, String description, String image, Date birthday, String phoneNumber, String gender, String email) {
        this.name = name;
        this.position = position;
        this.description = description;
        this.image = image;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getConfirmInfo() {
        return confirmInfo;
    }

    public void setConfirmInfo(String confirmInfo) {
        this.confirmInfo = confirmInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}