package com.example.project3mon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class TrainerListAdapter extends RecyclerView.Adapter<TrainerListAdapter.TrainerViewHolder> {

    private Context mContext;
    private List<User> mList;

    public TrainerListAdapter(Context context, List<User> mList) {
        this.mList = mList;
        this.mContext =context;
    }

    @NonNull
    @Override
    public TrainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_2, parent, false );
        return new TrainerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerViewHolder holder, int position) {
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
        Intent intent = new Intent(mContext, Trainer1Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", user);
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


    public class TrainerViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private CircularImageView imgAvatar;
        private LinearLayout layoutItem;

        public TrainerViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.layout_item_2);
            txtName = itemView.findViewById(R.id.txtName);
            imgAvatar = itemView.findViewById(R.id.img_avata);
        }
    }

}
