package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private Button btnTime,btnTime2;
    int hour,minute,hour2,minute2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        btnTime=findViewById(R.id.btnTime);
        btnTime2=findViewById(R.id.btnTime2);
    }

    public void clickToChooseTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectHour, int selectMinute) {
                hour=selectHour;
                minute=selectMinute;
                btnTime.setText("Từ: "+String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,onTimeSetListener,hour,minute,true);
        timePickerDialog.show();
    }

    public void clickToChooseTime2(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener =new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectHour, int selectMinute) {
                hour2=selectHour;
                minute2=selectMinute;
                btnTime2.setText("Đên: "+String.format(Locale.getDefault(),"%02d:%02d",hour2,minute2));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,onTimeSetListener,hour2,minute2,true);
        timePickerDialog.show();
    }

    public void clicktoBack2(View view) {
        finish();
    }
}