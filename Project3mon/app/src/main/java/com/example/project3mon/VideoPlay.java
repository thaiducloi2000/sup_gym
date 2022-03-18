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
import android.widget.VideoView;

import com.google.android.material.button.MaterialButton;

public class VideoPlay extends AppCompatActivity {

    private static final String VIDEO_SAMPLE =
            "https://firebasestorage.googleapis.com/v0/b/supgym-fd72d.appspot.com/o/video_gym_1.mp4?alt=media&token=21f9bb84-0030-4e7c-94c3-88c8dd30e361";

    private VideoView myVideo;
    private LinearLayout linearButton;
    private Button btnDone;
//    TextView txtDuration, txtCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        myVideo = findViewById(R.id.video);
        linearButton = findViewById(R.id.button);
        btnDone = findViewById(R.id.btnDone);

//        btnDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity( new Intent(VideoPlay.this, AddVideoActivity.class));
//            }
//        });

//        txtDuration = findViewById(R.id.duration);
//        txtCurrent = findViewById(R.id.current);

//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_gym_1;

        //Create MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(myVideo);

        // Parse Video Link
        Uri uri = Uri.parse(VIDEO_SAMPLE);

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