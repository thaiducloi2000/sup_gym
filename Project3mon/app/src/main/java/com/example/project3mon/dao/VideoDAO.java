package com.example.project3mon.dao;

import com.example.project3mon.DBUtils;
import com.example.project3mon.User;
import com.example.project3mon.dto.Video;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class VideoDAO {

    public List<Video> getUserVideo(int userID) throws SQLException {
        List<Video> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT videoName, videoUrl, background, checkmark, ID FROM tblVideo WHERE userID = ?";
                stm=conn.prepareStatement(sql);
                stm.setInt(1, userID);
                rs=stm.executeQuery();
                while (rs.next()){
                    String ID = rs.getString("ID");
                    String videoName = rs.getString("videoName");
                    String videoUrl = rs.getString("videoUrl");
                    String background = rs.getString("background");
                    String checkmark = rs.getString("checkmark");
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add( new Video(ID, videoName, videoUrl, background, checkmark));
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
        return list;
    }

    public  boolean AddNewVideo(Video video , int userID) throws SQLException {
        boolean check=false;
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="INSERT INTO tblVideo VALUES (?,?,?,?,?,?);";
                stm=conn.prepareStatement(sql);
                stm.setString(1,video.getVideoID());
                stm.setString(2,video.getVideoName());
                stm.setString(3,video.getVideoUrl());
                stm.setString(4, video.getBackground());
                stm.setInt(5, userID);
                stm.setString(6,video.getCheckMark());
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
