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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project3mon.dao.SaveDataDAO;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditTrainerProfileActivity extends AppCompatActivity{
    private Button txtBirthday;
    private int selectSex;
    private Spinner spSex;
    private EditText txtName, txtDescription, txtPhoneNumber, txtEmail;
    private RoundedImageView avatar;
    private int MEN =0 , WOMEN = 1, OTHER =2;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trainer_profile);
        avatar = findViewById(R.id.imageAvatarProfile);
        txtName = findViewById(R.id.txtNameProfile);
        txtDescription = findViewById(R.id.txtDescription);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        spSex = findViewById(R.id.spSex);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtEmail = findViewById(R.id.txtEmail);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        GetData data = new GetData();
        User userProfile = (User) bundle.get("userProfile");
        User user = null;
        try {
            user = data.getUserProfile(userProfile.getID()); // load lại db sau khi update
        } catch (Exception e) {
            e.printStackTrace();
        }
        int imgResID = this.getResources().getIdentifier(user.getImage(), "drawable", this.getPackageName());
        avatar.setImageResource(imgResID);
        txtName.setText(user.getName());
        txtDescription.setText(user.getDescription());
        txtPhoneNumber.setText(user.getPhoneNumber());
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
                selectSex = spSex.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        String gender = user.getGender();
        spSex.setSelection(OTHER);
        if(gender.equals("Nam")){
            spSex.setSelection(MEN);
        }if(gender.equals("Nữ")){
            spSex.setSelection(WOMEN);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String birthday = sdf.format(user.getBirthday());
        txtBirthday.setText(birthday);
        txtEmail.setText(user.getEmail());
    }

    public void clickToChangeBirthday(View view) {
        Calendar calendar=Calendar.getInstance();
        int Year=calendar.get(Calendar.YEAR);
        int Month=calendar.get(Calendar.MONTH);
        int Day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(EditTrainerProfileActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                mDateSetListener,Year,Month,Day);
        dialog.getWindow();
        dialog.show();
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int Year, int Month, int Day) {
                Month= Month+1;
                String day=Month + "-" + Day +"-" + Year;
                txtBirthday.setText(day);
            }
        };
    }

    public void clickToSaveTrainerProfile(View view) throws Exception {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User user = (User) bundle.get("userProfile");

        String name = txtName.getText().toString();
        String description = txtDescription.getText().toString();
        String phoneNumber = txtPhoneNumber.getText().toString();
        int gender = selectSex;
        String sex="Nam";
        if(gender==1){
            sex = "Nu";
        } if(gender == 2){
            sex = "Khac";
        }
        String date = txtBirthday.getText().toString();
        Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(date);
        String email = txtEmail.getText().toString();
        SaveDataDAO dao = new SaveDataDAO();

        User updateUser = new User(user.getID(), name,description,date1 , phoneNumber, sex, email);
        boolean result = dao.updateDataUser(updateUser);
        if(result){
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickToBack(View view) {
        finish();
    }
}