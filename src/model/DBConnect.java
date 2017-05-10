package model;



import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnect {
    public static Connection dbConnection(){
        Connection con= null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //.newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false", "root", "");

        }catch (Exception e){
            System.out.println(e);
        }
        return   con;

    }
}
    







