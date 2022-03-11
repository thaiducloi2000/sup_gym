package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Trainer1Activity extends AppCompatActivity{

    private LinearLayout layoutBooking;
    private MaterialButton btnBooking;
    private ImageView imgUser;
    private int CUSTOMER = 1;
    private int TRAINER = 2;
    private TextView txtName, txtDescription, txtGender, txtAge, txtPhone,txtEmail,txtNickName,txtPrice;

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

        btnBooking = findViewById(R.id.btnBooking);

        txtEmail=findViewById(R.id.txtEmail);
        txtNickName=findViewById(R.id.txtNickName);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        User user = (User) bundle.get("User");
        int imgResID = this.getResources().getIdentifier(user.getImage(), "drawable", this.getPackageName());
        imgUser.setImageResource(imgResID);
        txtName.setText(user.getName() + " - " + user.getPosition());
        txtDescription.setText(user.getDescription());
        txtGender.setText(user.getGender());
        Date birthday=user.getBirthday();
        int year=birthday.getYear();
        int curDate = Calendar.getInstance().getTime().getYear();
        int age=curDate-year;
        txtAge.setText(age+"");
        txtPhone.setText(user.getPhoneNumber());
        txtEmail.setText(user.getEmail());
        txtNickName.setText("@"+user.getImage());

        layoutBooking=findViewById(R.id.layoutBooking);

        int roleID = (int) bundle.get("roleID");
        if(roleID == CUSTOMER){
            layoutBooking.setVisibility(View.VISIBLE);
        }
        if(roleID == TRAINER){
            layoutBooking.setVisibility(View.GONE);
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