package model;



import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnect {

    public static Connection dbConnection(){

        Connection con= null;
        String sqlDriver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false";
        String sqlUserName = "root";
        String sqlPassword = "";

        try {
                //Introducing the Driver to the Class
            Class.forName(sqlDriver); //.newInstance();
            con = DriverManager.getConnection(url, sqlUserName, sqlPassword);

        }catch (Exception e){

            System.out.println(e);

        }

        return   con;

    }
}
    







