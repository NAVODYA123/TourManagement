package model;

import java.sql.*;

/**
 * Created by Gayan Kalinga on 07-May-17.
 */
public class InsertOperator {
    private String dbName, dbEmail, dbDOB, dbPWD, dbAccess;
    private String querySetData;
    private double dbSalary;

    public boolean insertOperator(String nameP, double salaryP, String emailP, String dobP,
                               String pwdP, String accessP) {
        dbSalary = salaryP;
        dbName = nameP;
        dbEmail = emailP;
        dbDOB = dobP;
        dbPWD = pwdP;
        dbAccess = accessP;
        boolean success=false;



        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false", "root", "");



            querySetData = "INSERT INTO operator (opr_salary, opr_name, opr_email, opr_date_of_birth, opr_password, opr_access_level)" +
                    "Values (?,?,?,?,?,?);";

            PreparedStatement setStmt = con.prepareStatement(querySetData);

            setStmt.setDouble(1, dbSalary);
            setStmt.setString(2, dbName);
            setStmt.setString(3, dbEmail);
            setStmt.setString(4, dbDOB);
            setStmt.setString(5, dbPWD);
            setStmt.setString(6, dbAccess);

            success=setStmt.execute();
            System.out.println("Successfully Inserted" + success);
            return success;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return success;

    }
}
