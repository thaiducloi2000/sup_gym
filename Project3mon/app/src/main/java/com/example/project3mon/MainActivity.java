package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickToRegister(View view) {
        Intent intent=new Intent(this,RoleActivity.class);
        startActivity(intent);
    }

    public void clickToLogin(View view) {
        Toast.makeText(this,"Dang Nhap Thanh Cong",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,HomeMenuActivity.class);
        startActivity(intent);
    }



}