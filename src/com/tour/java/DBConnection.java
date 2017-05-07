package com.tour.java;

import com.mysql.jdbc.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection{
    public void dbConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false", "root", "");
            Statement getStmt = con.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    
    public void checkTheDBAvailability (){
        dbConnection();


    }
}






