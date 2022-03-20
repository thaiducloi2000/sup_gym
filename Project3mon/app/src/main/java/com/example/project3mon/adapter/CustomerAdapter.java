package com.example.project3mon.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3mon.R;
import com.example.project3mon.Trainer1Activity;
import com.example.project3mon.User;
import com.example.project3mon.UserVideoList;
import com.example.project3mon.ViewUploadedVideo;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class CustomerAdapter extends  RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>{

    private Context mContext;
    private List<User> mList;
    private User trainer;


    public CustomerAdapter(Context mContext, List<User> mList, User trainer) {
        this.mContext = mContext;
        this.mList = mList;
        this.trainer = trainer;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_customer, parent, false );
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        User user = mList.get(position);
        if(user == null){
            return;
        }
        int imgResID = mContext.getResources().getIdentifier(user.getImage(), "drawable", mContext.getPackageName());
        holder.imgAvatar.setImageResource(imgResID);
        holder.txtName.setText(user.getName());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickToDetal(user);
            }
        });
    }

    private void onClickToDetal(User user) {
        Intent intent = new Intent(mContext, ViewUploadedVideo.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        bundle.putSerializable("trainer" , trainer);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private CircularImageView imgAvatar;
        private LinearLayout layoutItem;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.layout_customer);
            txtName = itemView.findViewById(R.id.txtName);
            imgAvatar = itemView.findViewById(R.id.img_avata);
        }
    }
}
