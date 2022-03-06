package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        Intent intent=new Intent(this,RegisterTrainerActivity.class);
        startActivity(intent);
    }
}