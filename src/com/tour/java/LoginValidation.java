package com.tour.java;

import java.sql.*;
/** B Gayan Kalinga IT 16040786 */

public class LoginValidation{


    private int loginStatus=0; /**LoginStatus 0 = Login Fails, LoginStatus 1= Login Success Login Status 3 = Locked uid*/
    private String queryGetData, queryLock, queryLockCount;
    private String dbUserID, dbPassword;
    private int dbLockCount, dbUidLock;


    public int loginCheck(String userID, String pwd){

        //System.out.println(userID + pwd);

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false", "root", "");
            Statement getStmt = con.createStatement();

            queryGetData = "SELECT operator_id,opr_password,uid_lock,lock_count  FROM operator;";
            getStmt.executeQuery(queryGetData);
            ResultSet rsGetData = getStmt.getResultSet();

            while (rsGetData.next()){
                dbUserID = rsGetData.getString("operator_id");
                dbPassword = rsGetData.getString("opr_password");
                dbUidLock = rsGetData.getInt("uid_lock");
                dbLockCount = rsGetData.getInt("lock_count");

                System.out.println(dbUserID+" -" + dbPassword + " -" +dbLockCount + " -" +dbUidLock);



                if (dbUserID.equals(userID) && dbPassword.equals(pwd) && dbUidLock == 1){
                /**UID Lock = 0  Then Locked if UID lock = 1 Not Lock (can only be 1 nd 0) */
                    System.out.println("OK");
                    loginStatus = 1;
                    return loginStatus;
                }

                else if (dbUidLock == 0){
                    System.out.println("UID Already Locked");

                    loginStatus = 3;
                    //return loginStatus;
                }
                else if ((!dbUserID.equals(userID) || !dbPassword.equals(pwd)) && dbUidLock == 1 ){


                    dbLockCount=dbLockCount+1;

                    if (dbLockCount<4 ){



                        queryLockCount = "Update operator SET lock_count=? where operator_id=?;";
                        PreparedStatement smtCountUpdate = con.prepareStatement(queryLockCount);

                        smtCountUpdate.setInt(1,dbLockCount);
                        smtCountUpdate.setInt(2,Integer.parseInt(userID));

                        smtCountUpdate.executeUpdate();

                        loginStatus=0;

                        System.out.println("You only have " + (3-dbLockCount)+ " " + "Login Attempts");
                        System.out.println("Db Count "+ dbLockCount);

                        //return loginStatus;

                    }else if(dbLockCount>=3){

                        queryLock = "Update operator SET  uid_lock=0 where operator_id=?;";
                        PreparedStatement smtLockUpdate = con.prepareStatement(queryLock);

                        //smtLockUpdate.setInt(1,dbLockCount);
                        smtLockUpdate.setInt(1,Integer.parseInt(dbUserID));

                        //queryLock = "Update operator SET uid_lock=? where operator_id=?;";
                        smtLockUpdate.executeUpdate();

                        loginStatus=3;
                        System.out.println("Uid Locked");

                        //return loginStatus;
                    }


                }

            }//end While



            } catch(Exception ex){
            ex.printStackTrace();
        }



        return loginStatus;

    }//end function
}//end class

/*
public class LoginValidation {
    public int loginCheck(String userid, String password){
            String query, queryLock,queryCount;
            String dbUsername, dbPassword;
            int login=0;
            int count=0;


            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false", "root", "");
                Statement stmt = (Statement) con.createStatement();

                query = "SELECT operator_id,opr_password,uid_lock,lock_count  FROM operator;";
                stmt.executeQuery(query);
                ResultSet rs = stmt.getResultSet();

                while(rs.next()){
                    dbUsername = rs.getString("operator_id");
                    dbPassword = rs.getString("opr_password");

                    if(dbUsername.equals(userid) && dbPassword.equals(password)){
                        System.out.println("OK");
                        login = 1;

                    }else {

                        count ++;
                        queryCount = "UPDATE `operator` SET `uid_count`= ? WHERE operator_id = ? ";
                        PreparedStatement smtCount = con.prepareStatement(queryCount);
                        smtCount.setInt(1, count);
                        smtCount.setInt(2,Integer.parseInt(userid));
                        smtCount.executeQuery();

                            if (count == 3) {
                                login = 3;
                                queryLock = "UPDATE `operator` SET `uid_lock`= 0 WHERE operator_id = ? ";
                                PreparedStatement smtLock = con.prepareStatement(queryLock);
                                smtLock.setInt(1, Integer.parseInt(userid));
                                smtLock.executeQuery();

                            } else {
                                login = 0;
                            }

                    }
                    //System.out.println(userid + password + " " + dbUsername + dbPassword);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return login;
        }
    } */

