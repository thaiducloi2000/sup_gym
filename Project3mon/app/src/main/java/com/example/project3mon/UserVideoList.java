package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.project3mon.adapter.VideoAdapter;
import com.example.project3mon.dto.Video;

import java.util.ArrayList;
import java.util.List;

public class UserVideoList extends AppCompatActivity {

    private RecyclerView rcvVideo;
    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        adapter = new VideoAdapter(this, getListVideo());

        rcvVideo = this.findViewById(R.id.rcv_video);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvVideo.setLayoutManager(linearLayoutManager);
        rcvVideo.setAdapter(adapter);

    }

    public List<Video> getListVideo (){
        List<Video> videos = new ArrayList<>();
        videos.add( new Video("video1","video_gym_1","exercise1", "bài nộp 1", "icon_checkmark_green"));
        videos.add( new Video("video2","video_gym_1","exercice2", "bài nộp 2", "icon_checkmark_gray"));
        videos.add( new Video("video3","video_gym_1", "exercice3", "bài nộp 3", "icon_checkmark_gray"));
        videos.add( new Video("video4","video_gym_1", "exercice4", "bài nộp 4", "icon_checkmark_gray"));
        return videos ;
    }

    public void clicktoBack2(View view) {
        finish();
    }
}