package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project3mon.adapter.VideoAdapter;
import com.example.project3mon.adapter.VideoUploadListAdapter;
import com.example.project3mon.dao.VideoDAO;
import com.example.project3mon.dto.Video;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewUploadedVideo extends AppCompatActivity {

    private RecyclerView rcvVideo;
    private VideoUploadListAdapter adapter;
    private TextView txtUploadVideo;

    Bundle bundle;
    User user, trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploaded_video);
        txtUploadVideo = findViewById(R.id.txtUploadVideo);

        bundle = getIntent().getExtras();
        if (bundle == null) {
        } else {

            user = (User) bundle.get("user");
            trainer = (User) bundle.get("trainer");

            if (trainer == null) {
                txtUploadVideo.setVisibility(View.VISIBLE);
            }
        }

        try {
            adapter = new VideoUploadListAdapter(this, getListVideo(), user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        rcvVideo = this.findViewById(R.id.rcv_exercise);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvVideo.setLayoutManager(linearLayoutManager);
        rcvVideo.setAdapter(adapter);
    }

    public List<Video> getListVideo() throws SQLException {
        List<Video> videos = new ArrayList<>();
        VideoDAO dao = new VideoDAO();
        videos = dao.getUserVideo(Integer.parseInt(user.getID()));
        return videos;
    }

    public void clicktoBack2(View view) {
        finish();
    }

    public void clickToUploadNewVideo(View view) {
        Intent intent = new Intent(ViewUploadedVideo.this, AddVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}