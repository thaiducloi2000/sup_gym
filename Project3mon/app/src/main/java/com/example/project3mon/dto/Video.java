package com.example.project3mon.dto;

import java.io.Serializable;

public class Video implements Serializable {

    private String videoUrl;
    private String background;
    private String videoName;

    public Video(String videoUrl, String background, String videoName) {
        this.videoUrl = videoUrl;
        this.background = background;
        this.videoName = videoName;
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

}
