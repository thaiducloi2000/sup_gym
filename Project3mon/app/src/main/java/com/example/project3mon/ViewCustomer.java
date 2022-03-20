package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewCustomer extends AppCompatActivity {

    private RecyclerView listCustomer;
    private CustomerAdapter CustomerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        String ID = (String) bundle.get("ID");
        try{
            CustomerAdapter=new CustomerAdapter(this,getListCustomer(ID),ID);
        }catch (Exception e) {
            e.printStackTrace();
        }
        listCustomer=findViewById(R.id.listCustomer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        listCustomer.setLayoutManager(linearLayoutManager);
        listCustomer.setAdapter(CustomerAdapter);
    }

    private List<User> getListCustomer(String ID) throws Exception {
        List<User> listCustomer = new ArrayList<>();
        GetData data = new GetData();
        listCustomer = data.getListCustomer(ID);
        return listCustomer;
    }
    public void clicktoBack2(View view) {
        finish();
    }
}