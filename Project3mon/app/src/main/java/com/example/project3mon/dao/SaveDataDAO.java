package com.example.project3mon.dao;

import static java.lang.System.currentTimeMillis;

import com.example.project3mon.DBUtils;
import com.example.project3mon.User;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public  boolean AddToUserAccount(User user) throws SQLException {
        boolean check=false;
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="INSERT INTO tblUserAccounts VALUES (?,?,?,?,?, ?,?,CONVERT(date, SYSDATETIME()),'Active','link',?,1);";
                stm=conn.prepareStatement(sql);
                stm.setString(1,user.getName());
                stm.setString(2,user.getPosition());
                stm.setString(3,user.getDescription());
                Date birthday = new Date(user.getBirthday().getTime());
                stm.setString(4, user.getImage());
                stm.setDate(5,birthday);
                stm.setString(6,user.getPhoneNumber());
                stm.setString(7,user.getGender());
                stm.setString(8,user.getEmail());
                int result = stm.executeUpdate();
                if(result > 0){
                    check = true;
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
        return check;
    }

    public String getID() throws SQLException {
        String id="";
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT TOP 1 ID FROM tblUserAccounts ORDER BY ID DESC" ;
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if (rs.next()){
                    String result=rs.getString("ID");
                    id+=result;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rs!=null){
                rs.close();
            }if(stm!=null){
                stm.close();
            }if(conn!=null){
                conn.close();
            }
        }
        return id;
    }

    public boolean CreateAccount(User user, String userName, String password) throws SQLException {
        boolean check=false;
        if(AddToUserAccount(user)){
            String id=getID();
            Connection conn=null;
            PreparedStatement stm=null;
            try {
                conn = DBUtils.openConnection();
                if(conn!=null){
                    String sql="INSERT INTO tblAccount VALUES (?,?,?)";
                    stm=conn.prepareStatement(sql);
                    stm.setString(1,userName);
                    stm.setString(2,password);
                    stm.setString(3,id);
                    int result = stm.executeUpdate();
                    if(result > 0){
                        check = true;
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
        }
        return check;
    }

    public boolean createSchedule() throws SQLException{
        boolean check = false;
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="INSERT INTO tblSchedules VALUES (?,?,?,?,?,?,?,'True')";
                stm=conn.prepareStatement(sql);
                Date date = new Date(System.currentTimeMillis());

                int result = stm.executeUpdate();
                if(result > 0){
                    check = true;
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
        return check;
    }
    public boolean saveWallet(float wallet, String userID) throws SQLException{
        boolean check = false;
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="Update tblUserAccounts Set wallet=? Where ID=?";
                stm=conn.prepareStatement(sql);
                stm.setFloat(1,wallet);
                stm.setString(2,userID);
                int result = stm.executeUpdate();
                if(result > 0){
                    check = true;
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
        return check;
    }
}
