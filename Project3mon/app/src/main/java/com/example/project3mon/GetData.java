package com.example.project3mon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
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

    public List<User> getListTrainer(int limit) throws SQLException {
        List<User> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT TOP (?) A.ID,fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID, B.price " +
                        " FROM tblUserAccounts A JOIN tblPrice B ON A.ID = B.trainerID " +
                        " WHERE status = 'Active' AND roleID = 2 ";
                stm=conn.prepareStatement(sql);
                stm.setInt(1, limit);
                rs=stm.executeQuery();
                while (rs.next()){
                    String ID = rs.getString("ID");
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
                    float price = rs.getFloat("price");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(ID,fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID, price));
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
                String sql="SELECT fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID, B.price" +
                        " FROM tblUserAccounts A JOIN tblPrice B ON A.ID = B.trainerID " +
                        " WHERE status = 'Active' AND gender = 'Nu' AND roleID = 2 ";
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
                    float price = rs.getFloat("price");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID, price));
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
                String sql="SELECT TOP 4 fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID, B.price " +
                        " FROM tblUserAccounts A JOIN tblPrice B ON A.ID = B.trainerID " +
                        " WHERE status = 'Active' AND roleID = 2 ORDER BY A.ID DESC";
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
                    float price = rs.getFloat("price");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID, price));
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
    public User getUserProfile(String id) throws Exception{
        User user = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try{
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql = "Select fullName,position, description, image, birthday, phoneNumber, gender, email from tblUserAccounts WHERE ID='"+id+"'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if(rs.next()){
                    String name = rs.getString("fullName");
                    String position = rs.getString("position");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String email = rs.getString("email");
//                    int roleID = rs.getInt("roleID");
                    user = new User(id,name,position,description, image, birthday, phoneNumber, gender, email);
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
        return user;
    }

    public List<User> getListHotTrainer() throws SQLException {
        List<User> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID, B.price " +
                        " FROM tblUserAccounts A JOIN tblPrice B ON A.ID = B.trainerID" +
                        " WHERE status = 'Active' AND roleID = 2 ORDER BY A.ID OFFSET 5 ROWS FETCH NEXT 5 ROWS ONLY ";
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
                    float price = rs.getFloat("price");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(fullName, position, description, image, birthday, phoneNumber, gender, confirmInfo, email, roleID, price));
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

    public List<Calendar> getListday(String id) throws SQLException {
        List<Calendar> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT sheduleDay " +
                        " FROM tblSchedules " +
                        " WHERE bookingID in (Select bookingID from tblBooking where customerID LIKE '"+id+"')";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    Date date=rs.getDate("sheduleDay");
                    Calendar day=Calendar.getInstance();
                    day.setTime(date);
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(day);
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

    public List<Calendar> getListdayTrainer(String id) throws SQLException {
        List<Calendar> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT sheduleDay " +
                        " FROM tblSchedules " +
                        " WHERE bookingID in (Select bookingID from tblBooking where trainerID LIKE '"+id+"')";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    Date date=rs.getDate("sheduleDay");
                    Calendar day=Calendar.getInstance();
                    day.setTime(date);
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(day);
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

    public List<User> getPersonalTrainer(String id) throws SQLException{
        List<User> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT fullName,image " +
                        " FROM tblUserAccounts" +
                        " WHERE ID IN (Select trainerID FROM tblBooking WHERE customerID LIKE '"+id+"' AND ID in (SELECT bookingID from tblSchedules)) ";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while (rs.next()){
                    String fullName = rs.getString("fullName");
                    String image = rs.getString("image");
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(fullName, "", "", image, null, "", "", "", "", 0, 0));
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

    public List<String> getSchedulesCustomer(String id,Date day) throws SQLException {
        List<String> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT startTime,endTime  " +
                        " FROM tblSchedules " +
                        " WHERE bookingID in (Select bookingID from tblBooking where customerID LIKE '"+id+"') AND sheduleDay LIKE CONVERT(date,?)";
                stm=conn.prepareStatement(sql);
                java.sql.Date ulDay=new java.sql.Date(day.getTime());
                stm.setDate(1, ulDay);
                rs=stm.executeQuery();
                if (rs.next()){
                    Time startTime=rs.getTime("startTime");
                    Time endTime=rs.getTime("endTime");
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    String s=startTime.getHours()+":"+String.format("%02d",startTime.getMinutes())+" - "+endTime.getHours()+":"+String.format("%02d",endTime.getMinutes());
                   list.add(s);
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


    public List<String> getSchedulesTrainer(String id,Date day) throws SQLException {
        List<String> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT startTime,endTime  " +
                        " FROM tblSchedules " +
                        " WHERE bookingID in (Select bookingID from tblBooking where trainerID LIKE '"+id+"') AND sheduleDay LIKE CONVERT(date,?)";
                stm=conn.prepareStatement(sql);
                java.sql.Date ulDay=new java.sql.Date(day.getTime());
                stm.setDate(1, ulDay);
                rs=stm.executeQuery();
                if (rs.next()){
                    Time startTime=rs.getTime("startTime");
                    Time endTime=rs.getTime("endTime");
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    String s=startTime.getHours()+":"+String.format("%02d",startTime.getMinutes())+" - "+endTime.getHours()+":"+String.format("%02d",endTime.getMinutes());
                    list.add(s);
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

    public String getNameByID(String userID) throws SQLException{
        String name="";
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="Select fullName FROM tblUserAccounts WHERE ID='"+userID+"'";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if (rs.next()){
                    name=rs.getString("fullName");
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
        return name;
    }
    public int getWalletByID(String userID)throws SQLException{
        int wallet= 0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="Select wallet FROM tblUserAccounts WHERE ID='"+userID+"'";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if (rs.next()){
                    wallet = rs.getInt("wallet");
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
        return wallet;
    }

    public String getSchedulesCustomerToday(String userID) throws SQLException {
        String today="";
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT startTime" +
                        " FROM tblSchedules " +
                        " WHERE bookingID in (Select bookingID from tblBooking where trainerID LIKE '"+userID+"') AND sheduleDay LIKE CONVERT(date,CONVERT(date, SYSDATETIME()))";
                stm=conn.prepareStatement(sql);

                rs=stm.executeQuery();
                if (rs.next()){
                    Time startTime=rs.getTime("startTime");
                    today=startTime.getHours()+":"+String.format("%02d",startTime.getMinutes());
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
        return today;
    }

    public List<User> getListCustomerToday(String id) throws SQLException {
        List<User> list=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try{
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql = "Select " +
                        "ID," +
                        "fullName," +
                        "position, " +
                        "description, " +
                        "image, " +
                        "birthday, " +
                        "phoneNumber, " +
                        "gender, " +
                        "email " +
                        "from " +
                        " tblUserAccounts " +
                        "WHERE ID IN " +
                        " (SELECT " +
                        "   customerID " +
                        "   FROM " +
                        "  tblBooking,tblSchedules " +
                        " WHERE " +
                        " tblBooking.ID=tblSchedules.bookingID AND " +
                        "  trainerID LIKE '"+id+"'AND " +
                        " tblSchedules.sheduleDay LIKE CONVERT(date,CONVERT(date, SYSDATETIME())))";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String name = rs.getString("fullName");
                    String position = rs.getString("position");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    String email = rs.getString("email");
                    if(list==null){
                        list=new ArrayList<>();
                    }
                    User user = new User(id,name,position,description, image, birthday, phoneNumber, gender, email);
                    list.add(user);
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

    public List<User> getListCustomer_2(int trainerID) throws SQLException {
        List<User> list = null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn = DBUtils.openConnection();
            if(conn!=null){
                String sql="SELECT A.ID, fullName, position, description, image, birthday, phoneNumber, gender, roleID \n" +
                        "From tblUserAccounts A Join tblBooking B ON A.ID = B.customerID Where B.trainerID = ?";
                stm=conn.prepareStatement(sql);
                stm.setInt(1, trainerID );
                rs=stm.executeQuery();
                while (rs.next()){
                    String id = String.valueOf(rs.getInt("ID"));
                    String fullName = rs.getString("fullName");
                    String position = rs.getString("position");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String gender = rs.getString("gender");
                    int roleID = rs.getInt("roleID");

                    if(list == null){
                        list = new ArrayList<>();
                    }
                    list.add(new User(id, fullName, position, description, image, birthday, phoneNumber, gender, roleID));
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
