package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
    }

    public void clickToCustomerRegister(View view) {
        Intent intent=new Intent(this,registerActivity.class);
        startActivity(intent);
    }

    public void clickToTrainerRegister(View view) {
        Intent intent=new Intent(RoleActivity.this,RegisterTrainerActivity.class);
        startActivity(intent);
    }
}