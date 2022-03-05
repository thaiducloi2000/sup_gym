package com.example.project3mon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetData {

    public String checkLogin(String userName, String password) throws SQLException {
        String ID=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="Select ID FROM tblAccount WHERE userName='"+userName+"' AND password='"+password+"'";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if (rs.next()){
                    ID=rs.getString("ID");
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
        return ID;
    }

    public int getRole(String ID) throws SQLException {
        int role=0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="Select roleID FROM tblUserAccounts WHERE ID='"+ID+"'";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if (rs.next()){
                    role=rs.getInt("roleID");
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
        return role;
    }
}
