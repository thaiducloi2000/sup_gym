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
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        int roleID = (int) bundle.get("roleID");
        try {
            trainerListAdapter = new TrainerListAdapter(this, getListTrainer(), roleID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rcvData = findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvData.setLayoutManager(linearLayoutManager);
        rcvData.setAdapter(trainerListAdapter);
    }

    private List<User> getListTrainer() throws Exception {
        List<User> listTrainer = new ArrayList<>();
        GetData data = new GetData();
        listTrainer = data.getListTrainer();
        return listTrainer;
    }


}