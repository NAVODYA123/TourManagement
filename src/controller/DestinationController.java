package controller;

/**
 * Created by Gayan Kalinga on 10-May-17.
 */
import com.mysql.jdbc.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DBConnect;
import model.Destination;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class DestinationController {

    @FXML private TextField cityNameTF,pricePerDayTF,minDaysTF;
    @FXML private TextField newCityNameTF,newPricePerDayTF,newMinDaysTF;
    @FXML private TableView <Destination> destinationTableView;
    @FXML private TableColumn <Destination, Integer> cityCode;
    @FXML private TableColumn <Destination, String> cityName;
    @FXML private TableColumn <Destination, Float> pricePerDay;
    @FXML private TableColumn <Destination, Integer> minDays;
    @FXML private Label cityIDLabel,cityNameLabel,pricePerDayLabel,minDayLabel;

    private ObservableList<Destination> destinationList = FXCollections.observableArrayList();
    private Main main;
    Connection con=DBConnect.dbConnection();

    public void initialize(){
        cityCode.setCellValueFactory(new PropertyValueFactory<>("cityCode"));
        cityName.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        pricePerDay.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        minDays.setCellValueFactory(new PropertyValueFactory<>("minDays"));

        showSelectedDestination(null);

        destinationTableView.getSelectionModel().selectedItemProperty().addListener(
                (destinationList, oldValue, newValue) -> showSelectedDestination(newValue));

    }

    public void setDestinationView(Main main){
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

            //System.out.println(queryInsertDestination);

            java.sql.PreparedStatement insertDestination = con.prepareStatement(queryInsertDestination);

            insertDestination.setString(1,cityNameforDB);
            insertDestination.setFloat(2,pricePerDayforDB);
            insertDestination.setInt(3,minDaysforDB);


            //System.out.println(insertDestination);

            insertDestination.execute();

            cityNameTF.clear();
            pricePerDayTF.clear();
            minDaysTF.clear();
            //setDestinationData();
            System.out.println("Successfully Inserted");
            destinationTableView.refresh();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }

    public void showSelectedDestination(Destination destination){

        if(destination!=null){
            cityIDLabel.setText(String.valueOf(destination.getCityCode()));
            cityNameLabel.setText(destination.getCityName());
            pricePerDayLabel.setText(String.valueOf(destination.getPricePerDay()));
            minDayLabel.setText(String.valueOf(destination.getMinDays()));
        }else{
            cityIDLabel.setText(" ");
            cityNameLabel.setText(" ");
            pricePerDayLabel.setText(" ");
            minDayLabel.setText(" ");
        }

    }

    public void deleteSelectedDestination(){

        String deleteDestinationQuery;
        int resultIndex =destinationTableView.getSelectionModel().getSelectedItem().getCityCode();

        deleteDestinationQuery="DELETE FROM destination WHERE city_code=?";

        try {

            java.sql.PreparedStatement deletePST = con.prepareStatement(deleteDestinationQuery);
            deletePST.setInt(1,resultIndex);

            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> resultBTn =deleteAlert.showAndWait();

            if(resultBTn.get()==ButtonType.OK){
                deletePST.execute();
                //destinationTableView.getItems().remove(destinationTableView.getSelectionModel().getFocusedIndex());
                //setDestinationData();
                destinationTableView.refresh();
                System.out.println("Successfully Deleted");
                cityIDLabel.setText(" ");
                cityNameLabel.setText(" ");
                pricePerDayLabel.setText(" ");
                minDayLabel.setText(" ");
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

    }

    public void updateSelectedDestination(){
        String newCityName;
        float newPricePerDay;
        int newMinDays;

        if(newCityNameTF.getText().equals("") || newPricePerDayTF.getText().equals("")  || newMinDaysTF.getText().equals("") ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }else {

            newCityName = newCityNameTF.getText();
            newPricePerDay = Float.parseFloat(newPricePerDayTF.getText());
            newMinDays = Integer.parseInt(newMinDaysTF.getText());


            int resultCityCode = destinationTableView.getSelectionModel().getSelectedItem().getCityCode();

            try {

                String updateDestinationQuery = "UPDATE destination SET city_name =?, price_per_day=?,minimum_days=? WHERE city_code=?";
                java.sql.PreparedStatement updateDestination = con.prepareStatement(updateDestinationQuery);

                updateDestination.setString(1, newCityName);
                updateDestination.setFloat(2, newPricePerDay);
                updateDestination.setInt(3, newMinDays);
                updateDestination.setInt(4, resultCityCode);

                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> resultBTn = deleteAlert.showAndWait();

                if (resultBTn.get() == ButtonType.OK) {
                    updateDestination.executeUpdate();
                    //destinationTableView.getItems().remove(destinationTableView.getSelectionModel().getFocusedIndex());
                    //setDestinationData();
                    destinationTableView.refresh();
                    System.out.println("Successfully Updated");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }

    }

}
