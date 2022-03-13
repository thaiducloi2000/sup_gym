package com.example.project3mon;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private Button btnTime,btnTime2;
    private TextView txtName,txtPrice,txtShow,txtNotiCheck,txtSumary;
    private RoundedImageView imageAvatar;
    int hour,minute,hour2,minute2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        btnTime=findViewById(R.id.btnTime);
        btnTime2=findViewById(R.id.btnTime2);
        txtName=findViewById(R.id.txtName);
        txtPrice=findViewById(R.id.txtPrice);
        imageAvatar=findViewById(R.id.imageAvatar);
        txtShow=findViewById(R.id.txtShow);
        txtNotiCheck=findViewById(R.id.txtNotiCheck);
        txtSumary=findViewById(R.id.txtSumary);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User user=(User) bundle.get("User");
        int imgResID = this.getResources().getIdentifier(user.getImage(), "drawable", this.getPackageName());
        imageAvatar.setImageResource(imgResID);
        txtName.setText(user.getName());
        txtPrice.setText((int) user.getPrice()+" VND/1h");
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        List<String> listday=new ArrayList<>();
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                int date=eventDay.getCalendar().getTime().getDate();
                int month=eventDay.getCalendar().getTime().getMonth()+1;
                String alo="";
                if(listday.contains(date+"")){
                    listday.remove(date+"");
                }else{
                    listday.add(date+"");
                }
                Collections.sort(listday);
                for (int i = 0; i < listday.size(); i++) {
                    if(i==(listday.size()-1)){
                        alo += listday.get(i);
                    }else{
                        alo += listday.get(i) + ",";
                    }
                }
                GetData dao=new GetData();
                List<String> list=new ArrayList<>();
                try {
                    list=dao.getSchedulesTrainer(user.getID(),eventDay.getCalendar().getTime());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                String message="";
                if(list!=null){
                    message+="Ngày "+date+" HLV bận vào khung giờ ";
                    for(int i=0;i<list.size();i++){
                        if(i==(list.size()-1)){
                            message+=list.get(i);
                        }else{
                            message+=list.get(i)+",";
                        }
                    }
                    txtNotiCheck.setBackground(getDrawable(R.drawable.custom_input_2));
                }else{
                    txtNotiCheck.setBackground(null);
                }
                txtNotiCheck.setText(message+"");
                txtShow.setText("Buổi tập sễ diễn ra vào ngày "+alo+" tháng " + month +" năm 2022");
                txtSumary.setText((int)(listday.size()*user.getPrice())+" VNĐ");
                }
        });
    }

    public void clickToChooseTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectHour, int selectMinute) {
                hour=selectHour;
                minute=selectMinute;
                btnTime.setText("Từ: "+String.format(Locale.getDefault(),"%02d:%02d",hour,minute));
                btnTime2.setText("Đến: "+String.format(Locale.getDefault(),"%02d:%02d",hour+1,minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,onTimeSetListener,hour,minute,true);
        timePickerDialog.show();
    }

    public void clicktoBack2(View view) {
        finish();
    }
}