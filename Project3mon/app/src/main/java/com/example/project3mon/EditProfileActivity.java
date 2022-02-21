package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button txtBirthday;
    private String selectSex;
    private Spinner spSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        spSex = findViewById(R.id.spSex);
        txtBirthday = findViewById(R.id.txtBirthday);
        List<String> dataSex = new ArrayList<>();
        dataSex.add("Nam");
        dataSex.add("Nữ");
        dataSex.add("Khác");
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.color_spinner_item, dataSex);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spSex.setAdapter(adapter);
        spSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectSex = spSex.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void clickToChangeBirthday(View view) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void clickToSaveUserProfile(View view) {
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int month = i1 + 1;
        String date = i2 + "/" + month + "/" + i;
        txtBirthday.setText(date);
    }
}