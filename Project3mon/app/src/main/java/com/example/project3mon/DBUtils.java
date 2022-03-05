package com.example.project3mon;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

    public static Connection openConnection()
    {
        StrictMode.ThreadPolicy a=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String url=("jdbc:jtds:sqlserver://10.0.2.2:1433;databaseName=TrainerRentalManagement");
            Connection con= DriverManager.getConnection(url,"sa","123456789");
            return con;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
