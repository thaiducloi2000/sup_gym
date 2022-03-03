package com.example.project3mon;

import java.io.Serializable;

public class User implements Serializable {

    private int resourceId;
    private String name;
    private String position;
    private String description;
    private String gender;
    private String age;
    private String phoneNumber;

    public User(int resourceId, String name, String position, String description, String gender, String age, String phoneNumber) {
        this.resourceId = resourceId;
        this.name = name;
        this.position = position;
        this.description = description;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}