package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3mon.adapter.VideoAdapter;
import com.example.project3mon.dao.VideoDAO;
import com.example.project3mon.dto.Video;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoList extends AppCompatActivity {

    private RecyclerView rcvVideo;
    private VideoAdapter adapter;
    private MaterialButton btnVideoUploaded;
    Bundle bundle;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        btnVideoUploaded = findViewById(R.id.btnVideoUploaded);

        bundle = getIntent().getExtras();
        user = (User) bundle.get("userProfile");

        if (user != null) {
            btnVideoUploaded.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VideoList.this, ViewUploadedVideo.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", user);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else {
            btnVideoUploaded.setText("Đăng Video");
            btnVideoUploaded.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VideoList.this, AddVideoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("check", true);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }


        try {
            adapter = new VideoAdapter(this, getListVideo(), user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        rcvVideo = this.findViewById(R.id.rcv_video);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvVideo.setLayoutManager(linearLayoutManager);
        rcvVideo.setAdapter(adapter);

    }

    public List<Video> getListVideo() throws SQLException {
        List<Video> videos = new ArrayList<>();
        VideoDAO dao = new VideoDAO();
        videos = dao.getUserVideo(4);
        return videos ;
    }

    public void clicktoBack2(View view) {
        finish();
    }

}