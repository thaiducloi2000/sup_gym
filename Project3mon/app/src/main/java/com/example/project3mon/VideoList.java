package com.example.project3mon;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        videos.add( new Video("video1","video_gym_1","exercise4", "tập toàn bộ các nhóm cơ", "icon_checkmark_green"));
        videos.add( new Video("video2","video_gym_1","exercice3", "bài tập khởi động hay", "icon_checkmark_gray"));
        videos.add( new Video("video3","video_gym_1", "exercice2", "bài tập cơ tay", "icon_checkmark_gray"));
        videos.add( new Video("video4","video_gym_1", "exercice1", "bài tập hít đất", "icon_checkmark_gray"));

        return videos ;
    }

    public void clicktoBack2(View view) {
        finish();
    }
}