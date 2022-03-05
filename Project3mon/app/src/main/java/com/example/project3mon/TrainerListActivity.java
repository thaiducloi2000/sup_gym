package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TrainerListActivity extends AppCompatActivity {

    private RecyclerView rcvData;
    private TrainerListAdapter trainerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        trainerListAdapter = new TrainerListAdapter(this, getListTrainer());
        rcvData = findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvData.setLayoutManager(linearLayoutManager);
        rcvData.setAdapter(trainerListAdapter);
    }

    private List<User> getListTrainer() {
        List<User> listTrainer = new ArrayList<>();
        listTrainer.add(
                new User(R.drawable.trainer1, "Trọng Thắng", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 7 nam", "Nam", "27","0351231230"));
        listTrainer.add(
                new User(R.drawable.trainer2, "Đức Lợi", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 10 nam, vô địch thế giới năm 2023", "Nam", "30","0354555666"));
        listTrainer.add(
                new User(R.drawable.trainer3, "Công Danh", "Personal Trainer",
                        "Trẻ Trung, Năng Động, Yêu Nghề", "Nam", "21","0351111111"));
        listTrainer.add(
                new User(R.drawable.trainer4, "Gia Huy", "Personal Trainer",
                        "Nhiệt Tình Với Công Việc", "Nam", "20","0342183282"));
        listTrainer.add(
                new User(R.drawable.trainer5, "Kim Long", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc ba-bốn nam", "Nam", "23","0351231230"));
        listTrainer.add(
                new User(R.drawable.trainer1, "Tấn Phát", "Personal Trainer",
                        "Sáu mui kinh nghiem pt duoc 6 nam", "Nam", "25","03558345837"));

        return listTrainer;
    }


}