package com.example.project3mon.dao;

import com.example.project3mon.DBUtils;
import com.example.project3mon.dto.HistoryRecharge;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class HistoryRechargeDAO implements Serializable {

    public List<HistoryRecharge> getListHistoryRecharge(String userID)throws SQLException {
        List<HistoryRecharge> result = new ArrayList<>();
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="Select dateRecharge, castCharge From tblHistoryRecharge WHERE userID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs=stm.executeQuery();
                while (rs.next()){
                    String date = rs.getString("dateRecharge");
                    float cast = rs.getFloat("castCharge");
                    result.add(new HistoryRecharge(date, cast));
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
        return result;
    }
}
