package com.example.project3mon;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

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
    private TextView txtName,txtPrice,txtShow,txtNotiCheck,txtSumary,txtTime;
    private RoundedImageView imageAvatar;
    int hour,minute,hour2,minute2;
    private AppCompatSpinner spChooseDate,spChooseDate2;
    private List<String> dateSelect,dateSelect2;
    private LinearLayout layoutspinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        txtTime=findViewById(R.id.txtTime);
        spChooseDate = findViewById(R.id.spChooseDate);
        layoutspinner=findViewById(R.id.layout_spinner);
        spChooseDate2=findViewById(R.id.spChooseDate2);
        dateSelect = new ArrayList<>();
        dateSelect.add("7:00 - 8:00");
        dateSelect.add("8:30 - 9:30");
        dateSelect.add("13:30 - 14:30");
        dateSelect.add("16:30 - 17:30");
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.color_spinner_item, dateSelect);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        dateSelect2 = new ArrayList<>();
        dateSelect2.add("7:00 - 8:00");
        dateSelect2.add("8:30 - 9:30");
        dateSelect2.add("10:00 - 11:00");
        dateSelect2.add("13:30 - 14:30");
        dateSelect2.add("15:00 - 16:00");
        dateSelect2.add("16:30 - 17:30");
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.color_spinner_item, dateSelect2);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spChooseDate.setAdapter(adapter);
        spChooseDate2.setAdapter(adapter2);
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
//                    message+="Ngày "+date+" HLV bận vào khung giờ ";
                    for(int i=0;i<list.size();i++){
                        if(i==(list.size()-1)){
                            message+=list.get(i);
                        }else{
                            message+=list.get(i)+",";
                        }
                    }
//                    txtNotiCheck.setBackground(getDrawable(R.drawable.custom_input_2));
                }else{
                    txtNotiCheck.setBackground(null);
                }
//                txtNotiCheck.setText(message+"");
                txtShow.setText("Buổi tập sễ diễn ra vào ngày "+alo+" tháng " + month +" năm 2022");
                txtSumary.setText((int)(listday.size()*user.getPrice())+" VNĐ");
                if(listday.size()==2){
                    txtTime.setText("Khung giờ buổi tập 1");
                    layoutspinner.setVisibility(View.VISIBLE);
                }else if(listday.size()==1||listday.size()==0){
                    txtTime.setText("Khung giờ buổi tập ");
                    layoutspinner.setVisibility(View.GONE);
                }
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

    public void clickToConfirmBooking(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }
}