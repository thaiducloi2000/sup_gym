package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Trainer1Activity extends AppCompatActivity {

    private ImageView imgUser;
    private TextView txtName, txtDescription, txtGender, txtAge, txtPhone;

    Format formatter = new SimpleDateFormat("yyyy-MM-dd");
    String s = "";

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
        int imgResID = this.getResources().getIdentifier(user.getImage(), "drawable", this.getPackageName());
        imgUser.setImageResource(imgResID);
        txtName.setText(user.getName());
        txtDescription.setText(user.getDescription());
        txtGender.setText(user.getGender());
//        txtAge.setText(formatter.format(user.getBirthday()));
        Date birthday=user.getBirthday();
        int year=birthday.getYear();
        int curDate = Calendar.getInstance().getTime().getYear();
        int age=curDate-year;
        txtAge.setText(age+"");
        txtPhone.setText(user.getPhoneNumber());

    }

    public void clickToBooking(View view) {
        Intent intent=new Intent(Trainer1Activity.this,BookingActivity.class);
        startActivity(intent);
    }
}