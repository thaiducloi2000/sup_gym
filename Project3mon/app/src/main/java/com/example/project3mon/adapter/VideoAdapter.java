package com.example.project3mon.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3mon.R;
import com.example.project3mon.VideoPlay;
import com.example.project3mon.dto.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHoler> {

    private Context mContext;
    private List<Video> list;

    public VideoAdapter(Context mContext, List<Video> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public VideoViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, parent, false );
        return new VideoViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHoler holder, int position) {
        Video video = list.get(position);
        if(video == null){
            return;
        }
        int imgResID = mContext.getResources().getIdentifier(video.getBackground(), "drawable", mContext.getPackageName());
        holder.txtVideoBackground.setImageResource(imgResID);

        int videoID = mContext.getResources().getIdentifier(video.getVideoUrl(), "raw", mContext.getPackageName());
        MediaPlayer mpl = MediaPlayer.create(mContext, videoID);
        int minute = mpl.getDuration() / 60000;
        String str = String.valueOf(mpl.getDuration() % 60000);
        String second = str.substring(0, 2);


        holder.txtVideoName.setText(video.getVideoName());
        holder.txtTimeDuration.setText(minute +":" + second);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickToDetal(video);
            }
        });
    }

    private void onClickToDetal(Video video) {
        Intent intent = new Intent(mContext, VideoPlay.class);
        Bundle bundle = new Bundle();
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

    public class VideoViewHoler extends RecyclerView.ViewHolder {

        private ImageView txtVideoBackground;
        private TextView txtVideoName, txtTimeDuration;
        private RelativeLayout layoutItem ;

        public VideoViewHoler(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.video_layout);
            txtVideoName = itemView.findViewById(R.id.videoName);
            txtTimeDuration = itemView.findViewById(R.id.videoDuration);
            txtVideoBackground = itemView.findViewById(R.id.videoBackground);
        }
    }

}
