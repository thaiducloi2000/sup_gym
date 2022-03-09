package com.example.project3mon.dto;

import java.io.Serializable;

public class Notification implements Serializable {
    private int ID;
    private String userID;
    private String trainerID;
    private String message;
    private boolean status;

    public Notification() {
    }

    public Notification(int ID, String userID, String trainerID, String message, boolean status) {
        this.ID = ID;
        this.userID = userID;
        this.trainerID = trainerID;
        this.message = message;
        this.status = status;
    }

    public Notification(String userID, String message) {
        this.userID = userID;
        this.message = message;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
