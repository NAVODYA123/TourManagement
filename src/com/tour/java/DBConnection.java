package com.tour.java;
/*
/**
 * Created by Gayan Kalinga on 07-May-17.
/*
import java.sql.*;
public class DBConnection {
    //jdbc:<driver protocol(>:<driver connection details>
    //for MS SQL = jdbc:odbc:tour.
    //for Mysql = jdbc:mysql://localhost:3306/tour;
    //for Oracle= jdbc:oracle:thin@myserver:1521:tour

    public int  DBConnection() {
        String url = "jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false";
        String user = "root";
        String pwd = "";

        Connection tourCon=null;
        PreparedStatement myStatement=null;
        ResultSet myResults=null;

        try {
            //1.Get a Connection to DB
            //Connection tourCon = DriverManager.getConnection(url, user, pwd);
            tourCon = DriverManager.getConnection(url, user, pwd);

            //2.Create a Statement
            //Statement myStatement = tourCon.createStatement();

            //Prepare Statement
            myStatement=tourCon.prepareStatement("select opr_access_level,operator_id from operator where operator_id=?");

            //Set Parameters
            myStatement.setInt(1, 7500);

            //3.Execute SQL Query
            //String sql="select * from operator";
           myResults = myStatement.executeQuery();

            //4.Process the result set
            while(myResults.next()){
                //read data from each row
              myResults.getInt("operator_id");
            }





        } catch (Exception exec) {
            exec.printStackTrace();
        }

    }


}

*/





