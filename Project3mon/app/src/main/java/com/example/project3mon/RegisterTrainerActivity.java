package com.example.project3mon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project3mon.dao.SaveDataDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterTrainerActivity extends AppCompatActivity {

    private Spinner spnGender;
    private TextView txtDOB;
    private EditText edtUsername,edtFullName,edtPhoneNumber,edtEmail,edtPassword;
    private static final String TAG= "RegisterTrainerActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_trainer);
        spnGender=findViewById(R.id.spnGender);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.custom_spinner,getResources().getStringArray(R.array.list));
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
                DatePickerDialog dialog=new DatePickerDialog(RegisterTrainerActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
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

    public void clickToRegisterTrainer(View view) throws ParseException, SQLException {
        edtUsername=findViewById(R.id.edtUsername);
        edtFullName=findViewById(R.id.edtFullName);
        edtPhoneNumber=findViewById(R.id.edtPhoneNumber);
        edtEmail=findViewById(R.id.edtEmail);
        txtDOB=findViewById(R.id.txtDOB);
        spnGender=findViewById(R.id.spnGender);
        edtPassword=findViewById(R.id.edtPassword);
        String gender="";
        String g=spnGender.getSelectedItem().toString();
        if(g.equals("Nam")){
            gender="Nam";
        }if(g.equals("N???")){
            gender="Nu";
        }if(g.equals("Khac")){
            gender="Khac";
        }
        String userName=edtUsername.getText().toString();
        String fullName=edtFullName.getText().toString();
        String phone=edtPhoneNumber.getText().toString();
        String email=edtEmail.getText().toString();
        String password=edtPassword.getText().toString();
        Date dob= new SimpleDateFormat("dd/MM/yyyy").parse(txtDOB.getText().toString());
        SaveDataDAO dao=new SaveDataDAO();
        User user=new User("",fullName,"Personal Trainer","Ch???ng c?? c??ng th???c b?? m???t n??o c???. T??i n??ng t??? n???ng, t??i t???p luy???n ch??m ch??? v?? t??i lu??n ?????t m???c ti??u tr??? th??nh ng?????i gi???i nh???t","trainer3",dob,phone,gender,email);
        boolean check=dao.CreateAccount(user,userName,password);
        if(check){
            Toast.makeText(this,"????ng K?? T??i Kho???n Th??nh C??ng",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent();
            startActivity(intent);
        }else{
            Toast.makeText(this,"????ng K?? T??i Kho???n Th???t B???i",Toast.LENGTH_SHORT).show();
        }
    }
}