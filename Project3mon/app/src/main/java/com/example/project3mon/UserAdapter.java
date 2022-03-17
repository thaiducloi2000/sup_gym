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

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private Context mContext;
    private List<User> mList;
    private int roleID;
    private String userID;

    public UserAdapter(Context context, List<User> mList, int roleID, String userID) {
        this.mList = mList;
        this.mContext =context;
        this.roleID = roleID;
        this.userID = userID;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getUserID() {
        return userID;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false );
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mList.get(position);
        if(user == null){
            return;
        }

        int imgResID = mContext.getResources().getIdentifier(user.getImage(), "drawable", mContext.getPackageName());
        holder.imageView.setImageResource(imgResID);

        String name[] = user.getName().split(" ");
        String shortName = name[name.length - 2] + " " + name[name.length - 1];

        holder.txtName.setText(shortName);
        holder.txtPrice.setText((int)user.getPrice()+ " VNĐ");

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
        bundle.putSerializable("roleID", getRoleID());
        bundle.putSerializable("userID", getUserID());
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

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txtName, txtPrice;
        private LinearLayout layoutItem;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.layout_item);
            imageView = itemView.findViewById(R.id.img_avata);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
