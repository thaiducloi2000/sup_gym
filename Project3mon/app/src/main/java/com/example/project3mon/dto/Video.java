package com.example.project3mon.dto;

import java.io.Serializable;

public class Video implements Serializable {

    private String videoID;
    private String videoName;
    private String videoUrl;
    private String background;
    private String checkMark;


    public Video(String videoID, String videoName, String videoUrl, String background, String checkMark) {
        this.videoID = videoID;
        this.videoName = videoName;
        this.videoUrl = videoUrl;
        this.background = background;
        this.checkMark = checkMark;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCheckMark() {
        return checkMark;
    }

    public void setCheckMark(String checkMark) {
        this.checkMark = checkMark;
    }
}
