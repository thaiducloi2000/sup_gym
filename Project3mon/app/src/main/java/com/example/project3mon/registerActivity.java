package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class registerActivity extends AppCompatActivity {

    private Spinner spnGender;
    private TextView txtDOB;
    private static final String TAG= "registerActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spnGender=findViewById(R.id.spnGender);
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.custom_spinner,getResources().getStringArray(R.array.list));
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spnGender.setAdapter(adapter);

        txtDOB=findViewById(R.id.txtDOB);
        txtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int Year=calendar.get(Calendar.YEAR);
                int Month=calendar.get(Calendar.MONTH);
                int Day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(registerActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,Year,Month,Day);
                dialog.getWindow();
                dialog.show();
            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int Year, int Month, int Day) {
                Month= Month+1;
                String day=Month + "/" + Day +"/" + Year;
                txtDOB.setText(day);
            }
        };
    }
}