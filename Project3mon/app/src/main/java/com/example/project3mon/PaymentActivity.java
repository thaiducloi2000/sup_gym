package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PaymentActivity extends AppCompatActivity {

    private String userID;
    private TextView txtName, txtTotalPrice, txtSupCoins;
    private int wallet, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        txtName = findViewById(R.id.txtNameProfile);
        txtSupCoins = findViewById(R.id.txtSupCoins);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        userID = (String) bundle.get("userID");
        String totalPrice = (String) bundle.get("totalPrice");
        total = Integer.parseInt(totalPrice.substring(0, totalPrice.length()-3).trim()) / 1000;
        GetData dao = new GetData();
        try {
            String name = dao.getNameByID(userID);
            txtName.setText(name);
            wallet = dao.getWalletByID(userID);
            txtSupCoins.setText(wallet + " SUP Coins");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        txtTotalPrice.setText(("Tổng: " + total + " SUP Coins"));

    }

    public void clickToBack(View view) {
        finish();
    }

    public void clickToConfirmPayment(View view) {
        int confirm = wallet - total;
        if(confirm < 0){
            Toast.makeText(this, "Số dư không đủ", Toast.LENGTH_SHORT).show();
        }else if(confirm >= 0){
            finish();
        }

    }


}