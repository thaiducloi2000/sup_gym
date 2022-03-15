package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project3mon.adapter.VideoAdapter;
import com.example.project3mon.dto.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoList extends AppCompatActivity {

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
        videos.add( new Video("video_gym_1","exercise4", "tập toàn bộ các nhóm cơ"));
        videos.add( new Video("video_gym_1","exercice3", "top 10 bai tập hay"));
        videos.add( new Video("video_gym_1", "exercice2", "top 5 bài tập cơ chân"));
        videos.add( new Video("video_gym_1", "exercice1", "top 5 bài tập hit đất"));
        return videos ;
    }

}