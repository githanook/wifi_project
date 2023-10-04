package com.example.wifi_project;

import javax.naming.*;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class wifiDao {
    PreparedStatement pstmt;
    ResultSet rs;
    Connection con;

    public void getCon(){
        String server = "localhost:3306";
        String user_name = "root";
        String password="rlagksdnr12!@";

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println("JDBC드라이버 문제 발생"+e.getMessage());
            e.printStackTrace();
        }

        try{
//            con = DriverManager.getConnection("jdbc:mysql://"+server+"/public_wifi"+"?useSSL=false",user_name,password);
            con = DriverManager.getConnection("jdbc:mysql://"+server+"/public_wifi",user_name,password);
            System.out.println("연결완료");
        }catch(SQLException e){
            System.out.println("연결오류"+e.getMessage());
            e.printStackTrace();
        }

    }// getCon end

    public void wifiInsert(wifiDto bean ){
        getCon();
        try{
            String sql = "insert into wifi_list (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bean.getX_SWIFI_MGR_NO());
            pstmt.setString(2, bean.getX_SWIFI_WRDOFC());
            pstmt.setString(3, bean.getX_SWIFI_MAIN_NM());
            pstmt.setString(4, bean.getX_SWIFI_ADRES1());
            pstmt.setString(5, bean.getX_SWIFI_ADRES2());
            pstmt.setString(6, bean.getX_SWIFI_INSTL_FLOOR());
            pstmt.setString(7, bean.getX_SWIFI_INSTL_TY());
            pstmt.setString(8, bean.getX_SWIFI_INSTL_MBY());
            pstmt.setString(9, bean.getX_SWIFI_SVC_SE());
            pstmt.setString(10, bean.getX_SWIFI_CMCWR());
            pstmt.setString(11, bean.getX_SWIFI_CNSTC_YEAR());
            pstmt.setString(12, bean.getX_SWIFI_INOUT_DOOR());
            pstmt.setString(13, bean.getX_SWIFI_REMARS3());
            pstmt.setDouble(14, bean.getLAT());
            pstmt.setDouble(15, bean.getLNT());
            pstmt.setString(16, bean.getWORK_DTTM());
            pstmt.executeUpdate();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public ArrayList<wifiDto> getDistanceList(Double lat, Double lnt){
        ArrayList<wifiDto> list = new ArrayList<>();
        getCon();
        try{
            String sql = "select (6371 * acos ( cos ( radians(?) ) * cos( radians( LAT ) ) * cos( radians( LNT) - radians(?) ) + sin ( radians(? ) )  * sin( radians( LAT) ))) AS distance, X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM, ID from wifi_list order by distance LIMIT 0 , 20";

            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, lat);
            pstmt.setDouble(2, lnt);
            pstmt.setDouble(3, lat);
            rs = pstmt.executeQuery();
            while(rs.next()){
                wifiDto bean = new wifiDto();
                bean.setDISTANCE(rs.getDouble(1));
                bean.setX_SWIFI_MGR_NO(rs.getString(2));
                bean.setX_SWIFI_WRDOFC(rs.getString(3));
                bean.setX_SWIFI_MAIN_NM(rs.getString(4));
                bean.setX_SWIFI_ADRES1(rs.getString(5));
                bean.setX_SWIFI_ADRES2(rs.getString(6));
                bean.setX_SWIFI_INSTL_FLOOR(rs.getString(7));
                bean.setX_SWIFI_INSTL_TY(rs.getString(8));
                bean.setX_SWIFI_INSTL_MBY(rs.getString(9));
                bean.setX_SWIFI_SVC_SE(rs.getString(10));
                bean.setX_SWIFI_CMCWR(rs.getString(11));
                bean.setX_SWIFI_CNSTC_YEAR(rs.getString(12));
                bean.setX_SWIFI_INOUT_DOOR(rs.getString(13));
                bean.setX_SWIFI_REMARS3(rs.getString(14));
                bean.setLAT(rs.getDouble(15));
                bean.setLNT(rs.getDouble(16));
                bean.setWORK_DTTM(rs.getString(17));
                bean.setID(rs.getInt(18));
                list.add(bean);
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }



    //===================================================================================================================
    //히스토리 테이블

    public void historyInsert(historyDto bean ){
        getCon();
        try{
            String sql = "insert into location_history (LAT,LNT) values(?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, bean.getLAT());
            pstmt.setDouble(2, bean.getLNT());
            pstmt.executeUpdate();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public ArrayList<historyDto> getHistoryList(){
        ArrayList<historyDto> list = new ArrayList<>();
        getCon();
        try{
            String sql = "select * from location_history ORDER BY ID DESC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                historyDto bean = new historyDto();
                bean.setID(rs.getInt(1));
                bean.setLAT(rs.getDouble(2));
                bean.setLNT(rs.getDouble(3));
                bean.setTIME(rs.getString(4));
                list.add(bean);
            }
//            System.out.println(list);
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void historyDelete(historyDto bean ){
        getCon();
        try{
            String sql = "delete from location_history where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bean.getID());
            pstmt.executeUpdate();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //=================================================================================
    //상세페이지
    public wifiDto wifiDetail(int id) {
        getCon();
        wifiDto board = null;
        try{
            String sql = "select * from wifi_list where ID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                board = new wifiDto();
                board.setID(rs.getInt(1));
                board.setX_SWIFI_MGR_NO(rs.getString(2));
                board.setX_SWIFI_WRDOFC(rs.getString(3));
                board.setX_SWIFI_MAIN_NM(rs.getString(4));
                board.setX_SWIFI_ADRES1(rs.getString(5));
                board.setX_SWIFI_ADRES2(rs.getString(6));
                board.setX_SWIFI_INSTL_FLOOR(rs.getString(7));
                board.setX_SWIFI_INSTL_TY(rs.getString(8));
                board.setX_SWIFI_INSTL_MBY(rs.getString(9));
                board.setX_SWIFI_SVC_SE(rs.getString(10));
                board.setX_SWIFI_CMCWR(rs.getString(11));
                board.setX_SWIFI_CNSTC_YEAR(rs.getString(12));
                board.setX_SWIFI_INOUT_DOOR(rs.getString(13));
                board.setX_SWIFI_REMARS3(rs.getString(14));
                board.setLAT(rs.getDouble(15));
                board.setLNT(rs.getDouble(16));
                board.setWORK_DTTM(rs.getString(17));
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return board;
    }

    //==================================================================================
    //북마크
    public void bookMarkInsert(bookMarkDto bean ){
        getCon();
        try{
            String sql = "insert into bookmark_list (NAME,NUM) values(?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bean.getNAME());
            pstmt.setInt(2,bean.getNUM());
            pstmt.executeUpdate();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<bookMarkDto> getBookMarkList(){
        ArrayList<bookMarkDto> list = new ArrayList<>();
        getCon();
        try{
            String sql = "select * from bookmark_list ORDER BY NUM DESC";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                bookMarkDto bean = new bookMarkDto();
                bean.setID(rs.getInt(1));
                bean.setNAME(rs.getString(2));
                bean.setNUM(rs.getInt(3));
                bean.setReg_date(rs.getString(4));
                bean.setUpd_date(rs.getString(5));
                list.add(bean);
            }
//            System.out.println(list);
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public bookMarkDto selectOneBookmark(int id) {
        getCon();
        bookMarkDto board = null;
        try{
            String sql = "select * from bookmark_list where ID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                board = new bookMarkDto();
                board.setID(rs.getInt(1));
                board.setNAME(rs.getString(2));
                board.setNUM(rs.getInt(3));
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return board;
    }

    public int updateBookMark(bookMarkDto bean){
        int cnt=0;
        getCon();
        try{
            String sql = "update bookmark_list set NAME=?, NUM=?, upd_date=CURRENT_TIMESTAMP() where ID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,bean.getNAME());
            pstmt.setInt(2,bean.getNUM());
            pstmt.setInt(3,bean.getID());
            cnt = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cnt;
    }

    public void bookmarkDelete(bookMarkDto bean ){
        getCon();
        try{
            String sql = "delete from bookmark_list where id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bean.getID());
            pstmt.executeUpdate();
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int updatebookmarkwifidetail(bookMarkDto bean){
        int cnt = 0;
        getCon();
        try{
            String sql = "update bookmark_list set wifidetailid=?, detailreg_date=CURRENT_TIMESTAMP() where ID=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,bean.getWifidetailid());
            pstmt.setInt(2,bean.getID());
            cnt=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cnt;
    }

    public ArrayList<bookmarkjoinDto> bookmarklist(){
        ArrayList<bookmarkjoinDto> list = new ArrayList<>();
        getCon();
        try{
            String sql = "select a.ID, a.NAME, a.wifidetailid, b.X_SWIFI_MAIN_NM, a.detailreg_date from bookmark_list a left join wifi_list b on a.wifidetailid = b.ID where a.wifidetailid is not null";
            pstmt=con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                bookmarkjoinDto bean = new bookmarkjoinDto();
                bean.setID(rs.getInt(1));
                bean.setNAME(rs.getString(2));
                bean.setWifidetailid(rs.getInt(3));
                bean.setX_SWIFI_MAIN_NM(rs.getString(4));
                bean.setDetailreg_date(rs.getString(5));
                list.add(bean);
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public bookmarkjoinDto deletebookmarkdetail(int id){
        getCon();
        bookmarkjoinDto board = null;
        try{
            String sql = "select a.ID, a.NAME, a.wifidetailid, b.X_SWIFI_MAIN_NM, a.detailreg_date from public_wifi.bookmark_list a left join public_wifi.wifi_list b on a.wifidetailid = b.ID where a.ID=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            if(rs.next()){
                board = new bookmarkjoinDto();
                board.setID(rs.getInt(1));
                board.setNAME(rs.getString(2));
                board.setWifidetailid(rs.getInt(3));
                board.setX_SWIFI_MAIN_NM(rs.getString(4));
                board.setDetailreg_date(rs.getString(5));
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return board;
    }

    public int deletebookmark(int key){
        int cnt=0;
        getCon();
        try{
            String sql = "update bookmark_list set wifidetailid=NULL, detailreg_date=NULL where ID=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,key);
            cnt=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cnt;
    }

}
