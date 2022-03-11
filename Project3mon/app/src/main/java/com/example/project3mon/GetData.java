package com.example.project3mon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetData {

    public GetData() {
    }

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

    public List<User> getListTrainer() throws SQLException {
        List<User> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID " +
                        " FROM tblUserAccounts WHERE status = 'Active' AND roleID = 2 ";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    String fullName = rs.getString("fullName");
                    String position = rs.getString("position");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String confirmInfo = rs.getString("confirmInfo");
                    String email = rs.getString("email");
                    int roleID = rs.getInt("roleID");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID));
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

    public List<User> getListFemaleTrainer() throws SQLException {
        List<User> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID " +
                        " FROM tblUserAccounts WHERE status = 'Active' AND gender = 'Nữ' AND roleID = 2";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    String fullName = rs.getString("fullName");
                    String position = rs.getString("position");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String confirmInfo = rs.getString("confirmInfo");
                    String email = rs.getString("email");
                    int roleID = rs.getInt("roleID");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID));
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


    public List<User> getListNewTrainer() throws SQLException {
        List<User> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT TOP 4 fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID " +
                        " FROM tblUserAccounts WHERE status = 'Active' ORDER BY ID DESC AND roleID = 2";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    String fullName = rs.getString("fullName");
                    String position = rs.getString("position");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String confirmInfo = rs.getString("confirmInfo");
                    String email = rs.getString("email");
                    int roleID = rs.getInt("roleID");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID));
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

    public List<User> getListHotTrainer() throws SQLException {
        List<User> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID " +
                        " FROM tblUserAccounts WHERE status = 'Active' AND roleID = 2 ORDER BY id OFFSET 5 ROWS FETCH NEXT 5 ROWS ONLY ";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    String fullName = rs.getString("fullName");
                    String position = rs.getString("position");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String confirmInfo = rs.getString("confirmInfo");
                    String email = rs.getString("email");
                    int roleID = rs.getInt("roleID");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID));
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



}
