package com.example.project3mon;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private Button btnTime,btnTime2, btnConfirm;
    private TextView txtName,txtPrice,txtShow,txtNotiCheck,txtSumary,txtTime;
    private RoundedImageView imageAvatar;
    int hour,minute,hour2,minute2;
    private AppCompatSpinner spChooseDate,spChooseDate2,spChooseDate3,spChooseDate4,spChooseDate5,spChooseDate6,spChooseDate7, spLoopOption;
    private List<String> dateSelect,dateSelect2, loop_option , listday, listDate, listTime;
    private LinearLayout layoutspinner7,layoutspinner2,layoutspinner3,layoutspinner4,layoutspinner5,layoutspinner6, layout_loop_option;
    private int loop;
    private String userID, selectedDate1, selectedDate2, selectedDate3,selectedDate4,selectedDate5,selectedDate6,selectedDate7;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        btnConfirm = findViewById(R.id.btnConfirmBooking);
        btnConfirm.setEnabled(false);
        txtTime=findViewById(R.id.txtTime);
        layoutspinner2 = findViewById(R.id.layout_spinner2);
        layoutspinner3 = findViewById(R.id.layout_spinner3);
        layoutspinner4 = findViewById(R.id.layout_spinner4);
        layoutspinner5 = findViewById(R.id.layout_spinner5);
        layoutspinner6 = findViewById(R.id.layout_spinner6);
        layoutspinner7 = findViewById(R.id.layout_spinner7);
        layout_loop_option = findViewById(R.id.layout_spinner_loop_option);

        spChooseDate = findViewById(R.id.spChooseDate);
        spChooseDate2 = findViewById(R.id.spChooseDate2);
        spChooseDate3 = findViewById(R.id.spChooseDate3);
        spChooseDate4 = findViewById(R.id.spChooseDate4);
        spChooseDate5 = findViewById(R.id.spChooseDate5);
        spChooseDate6 = findViewById(R.id.spChooseDate6);
        spChooseDate7 = findViewById(R.id.spChooseDate7);
        spLoopOption = findViewById(R.id.spLoopOption);

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
        ArrayAdapter adapter3 = new ArrayAdapter(this, R.layout.color_spinner_item, dateSelect2);
        adapter3.setDropDownViewResource(R.layout.spinner_dropdown_item);
        ArrayAdapter adapter4 = new ArrayAdapter(this, R.layout.color_spinner_item, dateSelect2);
        adapter4.setDropDownViewResource(R.layout.spinner_dropdown_item);
        ArrayAdapter adapter5 = new ArrayAdapter(this, R.layout.color_spinner_item, dateSelect2);
        adapter5.setDropDownViewResource(R.layout.spinner_dropdown_item);
        ArrayAdapter adapter6 = new ArrayAdapter(this, R.layout.color_spinner_item, dateSelect2);
        adapter6.setDropDownViewResource(R.layout.spinner_dropdown_item);
        ArrayAdapter adapter7 = new ArrayAdapter(this, R.layout.color_spinner_item, dateSelect2);
        adapter7.setDropDownViewResource(R.layout.spinner_dropdown_item);

        loop_option = new ArrayList<>();
        loop_option.add("Không lặp");
        loop_option.add("Lặp vào tuần tiếp theo");
        ArrayAdapter adapterLoopOption = new ArrayAdapter(this, R.layout.color_spinner_item, loop_option);
        adapterLoopOption.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spLoopOption.setAdapter(adapterLoopOption);

        spChooseDate.setAdapter(adapter);
        spChooseDate2.setAdapter(adapter2);
        spChooseDate3.setAdapter(adapter3);
        spChooseDate4.setAdapter(adapter4);
        spChooseDate5.setAdapter(adapter5);
        spChooseDate6.setAdapter(adapter6);
        spChooseDate7.setAdapter(adapter7);

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
        user=(User) bundle.get("User");
        int imgResID = this.getResources().getIdentifier(user.getImage(), "drawable", this.getPackageName());
        imageAvatar.setImageResource(imgResID);
        txtName.setText(user.getName());
        txtPrice.setText((int) user.getPrice()+" VND/1h");
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        listday=new ArrayList<>();
        listDate= new ArrayList<>();
        listTime= new ArrayList<>();
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                int date=eventDay.getCalendar().getTime().getDate();
                int month=eventDay.getCalendar().getTime().getMonth()+1;
                int year= eventDay.getCalendar().getTime().getYear();
                String alo = "";
                if (listday.contains(date + "/" + month)) {
                    listday.remove(date + "/" + month);
                    listDate.remove(date+"-"+month+"-"+year);
                } else {
                    listday.add(date + "/" + month);
                    listDate.add(date+"-"+month+"-"+year);
                }

                Collections.sort(listday);
                for (int i = 0; i < listday.size(); i++) {
                    if (i == (listday.size() - 1)) {
                        alo += listday.get(i);
                    } else {
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
                if(list==null){
                    txtNotiCheck.setBackground(null);
                }
                txtShow.setText("Buổi tập sễ diễn ra vào ngày "+alo+" năm 2022");
                txtSumary.setText((int)(listday.size()*user.getPrice())+" VNĐ");

                btnConfirm.setEnabled(true);
                layout_loop_option.setVisibility(View.GONE);
                spLoopOption.setSelection(0);
                spLoopOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        loop = spLoopOption.getSelectedItemPosition();
                        if(loop == 1){
                            txtSumary.setText((int)(listday.size()*user.getPrice()*2)+" VND");
                        }else if(loop == 0){
                            txtSumary.setText((int)(listday.size()*user.getPrice())+" VND");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                if (listday.size() == 2) {
                    txtTime.setText("Khung giờ buổi tập 1");
                    layoutspinner2.setVisibility(View.VISIBLE);
                    layoutspinner3.setVisibility(View.GONE);
                    layoutspinner4.setVisibility(View.GONE);
                    layoutspinner5.setVisibility(View.GONE);
                    layoutspinner6.setVisibility(View.GONE);
                    layoutspinner7.setVisibility(View.GONE);
                } else if (listday.size() == 1 || listday.size() == 0) {
                    txtTime.setText("Khung giờ buổi tập ");
                    layoutspinner2.setVisibility(View.GONE);
                    layoutspinner3.setVisibility(View.GONE);
                    layoutspinner4.setVisibility(View.GONE);
                    layoutspinner5.setVisibility(View.GONE);
                    layoutspinner6.setVisibility(View.GONE);
                    layoutspinner7.setVisibility(View.GONE);
                } else if (listday.size() == 3) {
                    txtTime.setText("Khung giờ buổi tập 1");
                    layoutspinner3.setVisibility(View.VISIBLE);
                    layoutspinner4.setVisibility(View.GONE);
                    layoutspinner5.setVisibility(View.GONE);
                    layoutspinner6.setVisibility(View.GONE);
                    layoutspinner7.setVisibility(View.GONE);
                } else if (listday.size() == 4) {
                    txtTime.setText("Khung giờ buổi tập 1");
                    layoutspinner4.setVisibility(View.VISIBLE);
                    layoutspinner5.setVisibility(View.GONE);
                    layoutspinner6.setVisibility(View.GONE);
                    layoutspinner7.setVisibility(View.GONE);
                } else if (listday.size() == 5) {
                    txtTime.setText("Khung giờ buổi tập 1");
                    layoutspinner5.setVisibility(View.VISIBLE);
                    layoutspinner6.setVisibility(View.GONE);
                    layoutspinner7.setVisibility(View.GONE);
                } else if (listday.size() == 6) {
                    txtTime.setText("Khung giờ buổi tập 1");
                    layoutspinner6.setVisibility(View.VISIBLE);
                    layoutspinner7.setVisibility(View.GONE);
                } else if (listday.size() == 7) {
                    txtTime.setText("Khung giờ buổi tập 1");
                    layoutspinner7.setVisibility(View.VISIBLE);
                }

                if(listday.size() >=3){
                    layout_loop_option.setVisibility(View.VISIBLE);
                }

                if(listday.size() >= 8){
                    Toast.makeText(BookingActivity.this, "Không được chọn quá 7 buổi", Toast.LENGTH_SHORT).show();
                    btnConfirm.setEnabled(false);
                }else if(listday.size() == 0){
                    btnConfirm.setEnabled(false);
                }
                selectedTime1();
                selectedTime2();
                selectedTime3();
                selectedTime4();
                selectedTime5();
                selectedTime6();
                selectedTime7();
            }
        });
        userID = (String) bundle.get("userID");
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("userID", userID);
        bundle.putSerializable("totalPrice", txtSumary.getText().toString());
        bundle.putSerializable("dateBooking", (Serializable) listDate);
        listTime.add(selectedDate1);
        if(selectedDate2 != null){
            listTime.add(selectedDate2);
        }
        if(selectedDate3 != null){
            listTime.add(selectedDate3);
        }
        if(selectedDate4 != null){
            listTime.add(selectedDate4);
        }
        if(selectedDate5 != null){
            listTime.add(selectedDate5);
        }
        if(selectedDate5 != null){
            listTime.add(selectedDate5);
        }
        if(selectedDate6 != null){
            listTime.add(selectedDate6);
        }
        if(selectedDate7 != null){
            listTime.add(selectedDate7);
        }
        bundle.putSerializable("bookingTime", (Serializable) listTime);
        intent.putExtras(bundle);
        startActivity(intent);
        listTime.clear();
    }

    public void selectedTime1(){
        spChooseDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate1 = spChooseDate.getSelectedItem().toString();
                Toast.makeText(BookingActivity.this, selectedDate1 + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void selectedTime2(){
        spChooseDate2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate2 = spChooseDate2.getSelectedItem().toString();
                Toast.makeText(BookingActivity.this, selectedDate2 + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void selectedTime3(){
        spChooseDate3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate3 = spChooseDate3.getSelectedItem().toString();
                Toast.makeText(BookingActivity.this, selectedDate3 + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void selectedTime4(){
            spChooseDate4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedDate4 = spChooseDate4.getSelectedItem().toString();
                    Toast.makeText(BookingActivity.this, selectedDate4 + "", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
    }
    public void selectedTime5(){
        spChooseDate5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate5 = spChooseDate5.getSelectedItem().toString();
                Toast.makeText(BookingActivity.this, selectedDate5 + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void selectedTime6(){
        spChooseDate6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate6 = spChooseDate6.getSelectedItem().toString();
                Toast.makeText(BookingActivity.this, selectedDate6 + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void selectedTime7(){
            spChooseDate7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedDate7 = spChooseDate7.getSelectedItem().toString();
                    Toast.makeText(BookingActivity.this, selectedDate7 + "", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
    }
}