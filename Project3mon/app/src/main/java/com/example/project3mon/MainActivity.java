package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private EditText edtUserID,edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickToRegister(View view) {
        Intent intent=new Intent(this,RoleActivity.class);
        startActivity(intent);
    }

    public void clickToLogin(View view) throws SQLException, Exception {
        edtUserID=findViewById(R.id.edtUserID);
        edtPassword=findViewById(R.id.edtPassword);
        GetData data=new GetData();
        String ID=data.checkLogin(edtUserID.getText().toString(),edtPassword.getText().toString());
        if (ID!=null){
            Bundle bundle=new Bundle();
            bundle.putSerializable("ID",ID);
            User userProfile = data.getUserProfile(ID);
            bundle.putSerializable("userProfile", userProfile);
            Toast.makeText(this,"Đăng Nhập Thành Công",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,HomeMenuActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Sai Thông Tin Đăng Nhập",Toast.LENGTH_SHORT).show();
        }
    }

}