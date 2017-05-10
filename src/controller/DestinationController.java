package controller;

/**
 * Created by Gayan Kalinga on 10-May-17.
 */
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DBConnect;
import model.Destination;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DestinationController {

    @FXML private TextField cityNameTF;
    @FXML private TextField pricePerDayTF;
    @FXML private TextField minDaysTF;
    @FXML private TableView <Destination> destinationTableView;
    @FXML private TableColumn <Destination, Integer> cityCode;
    @FXML private TableColumn <Destination, String> cityName;
    @FXML private TableColumn <Destination, Float> pricePerDay;
    @FXML private TableColumn <Destination, Integer> minDays;

    private ObservableList<Destination> destinationList = FXCollections.observableArrayList();
    private Main main;
    public void initialize(){
        cityCode.setCellValueFactory(new PropertyValueFactory<>("cityCode"));
        cityName.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        pricePerDay.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        minDays.setCellValueFactory(new PropertyValueFactory<>("minDays"));

    }

    public void setDesignationView(Main main, Stage secondaryStage){
        this.main=main;
        setDestinationData();
        destinationTableView.setItems(destinationList);

    }

    public void setDestinationData(){
        String queryGetDestination,dbCityName;
        int dbCityCode,dbMinDays;
        float dbPricePerDay;
        Connection con=DBConnect.dbConnection();

        try {

            Statement getDestination = con.createStatement();
            queryGetDestination="SELECT * FROM destination;";
            getDestination.executeQuery(queryGetDestination);
            ResultSet rsDestination = getDestination.getResultSet();

            while(rsDestination.next()){
                dbCityCode=rsDestination.getInt("city_code");
                dbCityName=rsDestination.getString("city_name");
                dbPricePerDay = rsDestination.getFloat("price_per_day");
                dbMinDays = rsDestination.getInt("minimum_days");
                destinationList.add(new Destination(dbCityCode,dbCityName,dbPricePerDay,dbMinDays));
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }


    public void insertNewDestination(){

        String cityNameforDB,queryInsertDestination;
        float pricePerDayforDB;
        int minDaysforDB;

        Connection con=DBConnect.dbConnection();

        cityNameforDB=cityNameTF.getText();
        pricePerDayforDB=Float.parseFloat(pricePerDayTF.getText());
        minDaysforDB=Integer.parseInt(minDaysTF.getText());

        try{
            queryInsertDestination="INSERT INTO destination (city_name,price_per_day,minimum_days) VALUES(?,?,?);";

            System.out.println(queryInsertDestination);

            java.sql.PreparedStatement insertDestination = con.prepareStatement(queryInsertDestination);

            insertDestination.setString(1,cityNameforDB);
            insertDestination.setFloat(2,pricePerDayforDB);
            insertDestination.setInt(3,minDaysforDB);


            System.out.println(insertDestination);

            insertDestination.execute();

            cityNameTF.clear();
            pricePerDayTF.clear();
            minDaysTF.clear();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }

}
