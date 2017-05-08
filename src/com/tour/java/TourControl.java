package com.tour.java;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Created by Gayan Kalinga on 08-May-17.
 */
public class TourControl {
    @FXML
    ComboBox operatorCB,guideCB,hotelCB,destinationCB,vehicleCB,roomCB,custNameCB;
    @FXML
    DatePicker endDateDP,startDateDP;
    @FXML
    TextField securityDepositTF,totalCostDP;


    /*public void getComboOperatorName(){


        String dbOperatorsQuery,data;
    try {

        dbOperatorsQuery="SELECT operator_id,opr_name" +
                         "From operator";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tour?autoReconnect=true&useSSL=false", "root", "");

        ObservableList<String> operatorComboString = FXCollections.observableArrayList();

        ResultSet operatorRS = con.createStatement().executeQuery(dbOperatorsQuery);

        while(operatorRS.next()){
            data=operatorRS.getString("operator_name");
            operatorComboString.add(operatorRS.getString("operator_name"));

            System.out.println(operatorComboString);
            System.out.println(data);
        }
        operatorCB.setItems(operatorComboString);






    }catch (Exception e){
        e.printStackTrace();
    }
}*/
}
