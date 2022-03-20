package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.project3mon.adapter.CustomerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerList extends AppCompatActivity {
    CustomerAdapter listAdapter;
    RecyclerView rcv_cutomerList;

    Bundle bundle;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        user = (User) bundle.get("userProfile");
        Toast.makeText(this, user.getName(), Toast.LENGTH_SHORT).show();

        try {
            listAdapter = new CustomerAdapter(this, getListCustomer(), user);
        } catch (Exception e) {
            e.printStackTrace();

        }

        rcv_cutomerList = findViewById(R.id.rcv_customerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_cutomerList.setLayoutManager(linearLayoutManager);
        rcv_cutomerList.setAdapter(listAdapter);

    }

    private List<User> getListCustomer() throws Exception {
        List<User> listCustomer = new ArrayList<>();
        GetData data = new GetData();
        listCustomer = data.getListCustomer_2(Integer.parseInt(user.getID()));
        return listCustomer;
    }

    public void clicktoBack2(View view) {
        finish();
    }
}