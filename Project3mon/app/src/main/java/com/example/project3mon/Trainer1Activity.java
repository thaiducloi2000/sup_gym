package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Trainer1Activity extends AppCompatActivity {

    private ImageView imgUser;
    private TextView txtName, txtDescription, txtGender, txtAge, txtPhone;
    private MaterialButton btnBooking;
    private int CUSTOMER = 1;
    private int TRAINER = 2;
    private TextView txtName, txtDescription, txtGender, txtAge, txtPhone,txtPrice;
    Format formatter = new SimpleDateFormat("yyyy-MM-dd");

    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer1);

        btnBooking = findViewById(R.id.btnBooking);

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

        if(user.getRoleID() == 1){
            btnBooking.setVisibility(View.GONE);
        }else{

        }

    }

    public void clickToBooking(View view) {
       Intent intent=new Intent(Trainer1Activity.this,BookingActivity.class);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User user = (User) bundle.get("User");
        TextView price=findViewById(R.id.txtPrice);
        bundle.putSerializable("Price",price.getText().toString());
        bundle.putSerializable("User",user);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}