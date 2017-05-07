package com.tour.java;

import java.sql.*;

/**
 * Created by Gayan Kalinga on 07-May-17.
 */
public class TourConnection {
    //jdbc:<driver protocol(>:<driver connection details>
    //for MS SQL = jdbc:odbc:tour.
    //for Mysql = jdbc:mysql://localhost:3306/tour;
    //for Oracle= jdbc:oracle:thin@myserver:1521:tour

    public void DBConnection() {

        String url = "jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false";
        String user = "root";
        String pwd = "";

        Connection tourCon=null;
        PreparedStatement myStatement=null;
        ResultSet myResults=null;

        try{
            //Get Connection to DB
            tourCon = DriverManager.getConnection(url,user,pwd);
            System.out.println("connection Successful");


        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
