package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.project3mon.dto.Video;
import com.google.android.material.button.MaterialButton;

public class VideoPlay extends AppCompatActivity {

    private VideoView myVideo;
    private LinearLayout linearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        myVideo = findViewById(R.id.video);
        linearButton = findViewById(R.id.button);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Video video = (Video) bundle.get("videoPlay");

//        txtDuration = findViewById(R.id.duration);
//        txtCurrent = findViewById(R.id.current);

//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_gym_1;

        //Create MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(myVideo);
        Toast.makeText(this, video.getVideoUrl(), Toast.LENGTH_SHORT).show();

        // Parse Video Link
        Uri uri = Uri.parse(video.getVideoUrl());

        // set MediaController And Video Uri
        myVideo.setMediaController(mediaController);
        myVideo.setVideoURI(uri);

        new updateView().execute();

    }


    private class updateView extends AsyncTask<Void, Integer, Void> {

        int duration = 0;
        int current = 0;
        int percent = 0;

        @Override
        protected Void doInBackground(Void... params) {

            myVideo.start();
            myVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    duration = myVideo.getDuration();
//                    txtDuration.setText(duration + "");
                }
            });

            do {
                current = myVideo.getCurrentPosition();
                try {
                    publishProgress((int) current);
                    percent = ((current * 100) / duration);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (percent <= 80);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            System.out.println(values[0]);
//            txtCurrent.setText(current + "");
            if(percent > 80){
                linearButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void clickToCheckMark(View view) {
        finish();
    }

    public void clicktoBack2(View view) {
        finish();
    }

}