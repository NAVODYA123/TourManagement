package model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private Label userNameLabel;

    @FXML
    private Button signOutBTN;

    @FXML
    private Button exitBTN;

    @FXML
    private Button addNewCustomerBTN;

    @FXML
    private Button addNewTourBTN;

    @FXML
    private Button listOfTourBTN;

    @FXML
    private Button listOfVehicleBTN;

    @FXML
    private Button listOfDestinationBTN;

    @FXML
    private Button listOfGuideBTN;

    @FXML
    private Button adminFunctionBTN;

    @FXML
    void handleInsertTourBTN(ActionEvent event) {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/InsertTour.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add New Tour");
            stage.getIcons().add(new Image("view/Icons/ApplicationImage.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }

    }

}









