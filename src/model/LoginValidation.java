package model;

/**
 * B Gayan Kalinga IT 16040786 STII Project Tour Management Finished 2017-05-09
 * Login Validation For User ID and Password
 * Parameters User ID as a String and Password as a String
 * User ID Will lock after 03 attempts of invalid Password
 */

import java.sql.*;


public class LoginValidation {

    public String userValidation(String userID, String userPassword) {
        String dbUserID,dbPassword,queryGetData;
        int dbLockCount,dbUidLock;
        System.out.println(userID);

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false", "root", "");
            Statement getStmt = con.createStatement();
            queryGetData = "SELECT operator_id,uid_lock,opr_password,lock_count FROM operator;";
            getStmt.executeQuery(queryGetData);
            ResultSet rsGetData = getStmt.getResultSet();

            while (rsGetData.next()) {
                dbUserID = rsGetData.getString("operator_id");
                dbUidLock = rsGetData.getInt("uid_lock");
                dbPassword = rsGetData.getString("opr_password");
                dbLockCount = rsGetData.getInt("lock_count");

                if (dbUserID.equals(userID)) {
                    if (dbUidLock == 0) {
                        return "User ID Already Locked";
                    } else {

                        if (dbPassword.equals(userPassword)) {
                            return "ValidLogin";
                        } else {
                            dbLockCount++;

                            if (dbLockCount < 4) {
                                String updateLockCountQuery = "UPDATE operator SET lock_count=? WHERE operator_id=?;";
                                PreparedStatement updateLockCount = con.prepareStatement(updateLockCountQuery);
                                updateLockCount.setInt(1, dbLockCount);
                                updateLockCount.setString(2, dbUserID);
                                updateLockCount.executeUpdate();

                                String rtn = Integer.toString(3 - dbLockCount);
                                return rtn + "Login Attempts Remaining";
                            } else {
                                String updateUIDLock = "UPDATE  operator SET uid_lock=1 WHERE operator_id=?;";
                                PreparedStatement updateLockCount = con.prepareStatement(updateUIDLock);
                                updateLockCount.setString(1, dbUserID);
                                updateLockCount.executeUpdate();

                                return "User ID Now Locked";
                            }


                        }
                    }

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "User ID "+userID+" Not Found";
    }
}








