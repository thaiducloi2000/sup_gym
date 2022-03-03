package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Trainer1Activity extends AppCompatActivity {

    private ImageView imgUser;
    private TextView txtName, txtDescription, txtGender, txtAge, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer1);

        imgUser = findViewById(R.id.imageView1);
        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.introductionTxt);
        txtGender = findViewById(R.id.genderTxt);
        txtAge = findViewById(R.id.ageTxt);
        txtPhone = findViewById(R.id.phoneTxt);
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