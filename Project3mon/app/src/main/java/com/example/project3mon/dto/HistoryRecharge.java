package com.example.project3mon.dto;

import java.io.Serializable;

public class HistoryRecharge implements Serializable {
    private String ID;
    private String userID;
    private String date;
    private float cast;

    public HistoryRecharge(String userID, String date, float cast) {
        this.userID = userID;
        this.date = date;
        this.cast = cast;
    }

    public HistoryRecharge(String date, float cast) {
        this.date = date;
        this.cast = cast;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getCast() {
        return cast;
    }

    public void setCast(float cast) {
        this.cast = cast;
    }
}
