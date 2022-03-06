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

    public void clickToLogin(View view) throws SQLException {
        edtUserID=(EditText) findViewById(R.id.edtUserID);
        edtPassword=(EditText) findViewById(R.id.edtPassword);
        DBUtils c=new DBUtils();
        Connection connection=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            connection = c.openConnection();
            if(connection != null){
                String sql="Select ID FROM tblAccount WHERE userName='"+edtUserID.getText()+"' AND password='"+edtPassword.getText()+"'";
                stm=connection.prepareStatement(sql);
                rs= stm.executeQuery();
                if (rs.next()){
                    Toast.makeText(this,"Đăng Nhập Thành Công",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,HomeMenuActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this,"Sai Thông Tin Đăng Nhập",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        GetData data=new GetData();
        String ID=data.checkLogin(edtUserID.getText().toString(),edtPassword.getText().toString());
        if (ID!=null){
            Bundle bundle=new Bundle();
            bundle.putSerializable("ID",ID);
            Toast.makeText(this,"Đăng Nhập Thành Công",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,HomeMenuActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Sai Thông Tin Đăng Nhập",Toast.LENGTH_SHORT).show();
        }
    }

}