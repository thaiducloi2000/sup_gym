package com.example.project3mon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void clickToLogin(View view) throws SQLException {
        edtUserID=(EditText) findViewById(R.id.edtUserID);
        edtPassword=(EditText) findViewById(R.id.edtPassword);
        DBUtils c=new DBUtils();
        Connection connection=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            connection = c.openConnection();
            if(connection!=null){
                String sql="Select ID FROM tblAccount WHERE userName='"+edtUserID.getText()+"' AND password='"+edtPassword.getText()+"'";
                stm=connection.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    Toast.makeText(this,"Đăng Nhập Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,HomeMenuActivity.class);
                    startActivity(intent);
                }if(!rs.next()){
                    Toast.makeText(this,"Sai Thông Tin Đăng Nhập",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Sai Thông Tin Đăng Nhập",Toast.LENGTH_SHORT).show();
        }finally {
            if(rs!=null){
                rs.close();
            }if(stm!=null){
                stm.close();
            }if(connection!=null){
                connection.close();
            }
        }

    }
}