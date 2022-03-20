package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.project3mon.adapter.VideoAdapter;
import com.example.project3mon.dao.VideoDAO;
import com.example.project3mon.dto.Video;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserVideoList extends AppCompatActivity {

    private RecyclerView rcvVideo;
    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        try {
            adapter = new VideoAdapter(this, getListVideo());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        rcvVideo = this.findViewById(R.id.rcv_video);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvVideo.setLayoutManager(linearLayoutManager);
        rcvVideo.setAdapter(adapter);

    }

    public List<Video> getListVideo () throws SQLException {
        List<Video> videos = new ArrayList<>();
        VideoDAO dao = new VideoDAO();
        videos = dao.getUserVideo(4);
        return videos ;
    }

    public void clicktoBack2(View view) {
        finish();
    }
}