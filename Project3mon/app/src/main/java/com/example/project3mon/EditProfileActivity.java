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
import java.util.Date;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Button txtBirthday;
    private int selectSex;
    private Spinner spSex;
    private EditText txtName, txtPhoneNumber, txtEmail;
    private RoundedImageView avatar;
    private int MEN =0 , WOMEN = 1, OTHER =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        avatar = findViewById(R.id.imageAvatarProfile);
        txtName = findViewById(R.id.txtNameProfile);
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
        try {
            User user = data.getUserProfile(userProfile.getID()); // load lại db sau khi update

            int imgResID = this.getResources().getIdentifier(user.getImage(), "drawable", this.getPackageName());
            avatar.setImageResource(imgResID);
            txtName.setText(user.getName());
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
            if (gender.equals("Nam")) {
                spSex.setSelection(MEN);
            }
            if (gender.equals("Nu")) {
                spSex.setSelection(WOMEN);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String birthday = sdf.format(user.getBirthday());
            txtBirthday.setText(birthday);
            txtEmail.setText(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToChangeBirthday(View view) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void clickToSaveUserProfile(View view) throws Exception {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        User user = (User) bundle.get("userProfile");

        String name = txtName.getText().toString();
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

        User updateUser = new User(user.getID(), name,"",date1 , phoneNumber, sex, email);
        boolean result = dao.updateDataUser(updateUser);
        if(result){
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int month = i1 + 1;
        String date = i2 + "/" + month + "/" + i;
        txtBirthday.setText(date);
    }

    public void clickToBack(View view) {
        finish();
    }
}