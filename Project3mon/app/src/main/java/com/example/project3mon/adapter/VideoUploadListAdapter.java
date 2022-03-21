package com.example.project3mon.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3mon.AddVideoActivity;
import com.example.project3mon.R;
import com.example.project3mon.User;
import com.example.project3mon.VideoPlay;
import com.example.project3mon.dto.Video;

import java.util.List;

public class VideoUploadListAdapter extends RecyclerView.Adapter<VideoUploadListAdapter.VideoUploadListHolder>{

    private Context mContext;
    private List<Video> list;
    private User user;

    public VideoUploadListAdapter(Context mContext, List<Video> list, User user) {
        this.mContext = mContext;
        this.list = list;
        this.user = user;
    }

    @NonNull
    @Override
    public VideoUploadListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_exercise, parent, false );
        return new VideoUploadListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoUploadListHolder holder, int position) {
        Video video = list.get(position);
        if(video == null){
            return;
        }
//        holder.txtCheckMark.setImageResource(markResID);
        holder.txtExerciseName.setText(video.getVideoName());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickToDetal(video);
            }
        });
    }

    private void onClickToDetal(Video video) {
        Intent intent = new Intent(mContext, AddVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        bundle.putSerializable("video", video);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }


    public class VideoUploadListHolder extends RecyclerView.ViewHolder {

        private ImageView txtCheckMark;
        private TextView txtExerciseName, txtEvaluate;
        private LinearLayout layoutItem;

        public VideoUploadListHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.layout_exercise);
            txtExerciseName = itemView.findViewById(R.id.txtExercise);
            txtCheckMark = itemView.findViewById(R.id.checkMark);
        }
    }

}
