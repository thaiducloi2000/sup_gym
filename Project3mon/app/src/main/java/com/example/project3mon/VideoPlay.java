package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoPlay extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        videoView = findViewById(R.id.video);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_gym_1;

        //Create MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        //Parse Video Link
        Uri uri = Uri.parse(videoPath);

        // set MediaController And Video Uri
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);

        //Start Video
        videoView.start();

    }
}