package com.example.project3mon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Trainer1Activity extends AppCompatActivity {

    private ImageView imgUser;
    private TextView txtName, txtDescription, txtGender, txtAge, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer1);

        imgUser = findViewById(R.id.imageView1);
        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        txtGender = findViewById(R.id.txtGender);
        txtAge = findViewById(R.id.txtAge);
        txtPhone = findViewById(R.id.txtPhoneNumber);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        User user = (User) bundle.get("User");
        imgUser.setImageResource(user.getResourceId());
        txtName.setText(user.getName());
        txtDescription.setText(user.getDescription());
        txtGender.setText(user.getGender());
        txtAge.setText(user.getAge());
        txtPhone.setText(user.getPhoneNumber());

    }

}