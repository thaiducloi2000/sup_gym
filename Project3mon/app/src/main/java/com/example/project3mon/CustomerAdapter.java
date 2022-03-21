package com.example.project3mon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>{


    private Context mContext;
    private List<User> mList;
    private String userID;


    public CustomerAdapter(Context mContext, List<User> mList,String userID) {
        this.mContext = mContext;
        this.mList = mList;
        this.userID=userID;
    }

    public String getUserID() {
        return userID;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item, parent, false );
        return new CustomerAdapter.CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

        User user = mList.get(position);
        if(user == null){
            return;
        }
        if(user.getImage()!=null){
            int imgResID = mContext.getResources().getIdentifier(user.getImage(), "drawable", mContext.getPackageName());
            holder.imageView.setImageResource(imgResID);
        }else{
            holder.imageView.setImageResource(R.drawable.avatar_default);
        }
        String name[] = user.getName().split(" ");
        String shortName = name[name.length - 2] + " " + name[name.length - 1];

        holder.txtName.setText("Họ Tên: "+shortName);
        GetData data=new GetData();
        Calendar calendar=Calendar.getInstance();
        Date today=new Date();
        today.setYear(calendar.getTime().getYear());
        try {
            String time=data.getSchedulesCustomerToday(userID,today);
            holder.txtSchudulesTime.setText("Giờ bắt đầu: "+time);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Date birthday=user.getBirthday();
        int year=birthday.getYear();
        int curDate = Calendar.getInstance().getTime().getYear();
        int age=curDate-year;
        holder.txtAge.setText("Tuổi :"+age);
    }

    @Override
    public int getItemCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txtName, txtAge,txtSchudulesTime;
        private LinearLayout layoutItem;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.customer_item);
            imageView = itemView.findViewById(R.id.img_avata);
            txtName = itemView.findViewById(R.id.txtName);
            txtSchudulesTime = itemView.findViewById(R.id.txtSchudulesTime);
            txtAge = itemView.findViewById(R.id.txtAge);
        }
    }
}
