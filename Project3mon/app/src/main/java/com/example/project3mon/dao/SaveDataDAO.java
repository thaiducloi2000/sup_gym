package com.example.project3mon.dao;

import com.example.project3mon.DBUtils;
import com.example.project3mon.User;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class SaveDataDAO implements Serializable {
    public SaveDataDAO() {
    }

    public boolean updateDataUser(User user) throws Exception{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="Update tblUserAccounts Set fullName=?, description=?, birthday=?, phoneNumber=?, gender=?, email=? WHERE ID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1,user.getName());
                stm.setString(2,user.getDescription());
                Date birthday = new Date(user.getBirthday().getTime());
                stm.setDate(3, birthday);
                stm.setString(4,user.getPhoneNumber());
                stm.setString(5,user.getGender());
                stm.setString(6,user.getEmail());
                stm.setString(7,user.getID());
                int result = stm.executeUpdate();
                if(result > 0){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(stm!=null){
                stm.close();
            }if(conn!=null){
                conn.close();
            }
        }
        return false;
    }
}
