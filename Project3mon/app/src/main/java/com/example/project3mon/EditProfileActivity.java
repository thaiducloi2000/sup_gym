package com.example.project3mon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    private String selectSex;
    private Spinner spSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        spSex = findViewById(R.id.spSex);
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
    }

    public void clickToSaveUserProfile(View view) {
    }
}