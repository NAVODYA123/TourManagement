package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application{

    private Stage primaryStage;

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage){
        this.primaryStage=primaryStage;
        loginWindow();
    }

    //Open Login Form when the System Lords
    public void loginWindow(){
        try{

                //Instantiate FXML Loader  and give the View file path
            FXMLLoader loginFormLoader= new FXMLLoader(Main.class.getResource("/view/LoginFormView.fxml"));
                //Root Node inside fxml file is loaded by FXML Loader
            AnchorPane loginPane = loginFormLoader.load();
                //Connect with Login Controller Class
            LoginFormController loginFormController = loginFormLoader.getController();
                //setMain method inside Login Form Controller class is called with mainWindow parameter
            loginFormController.setMain(this);
                //Instantiate Scene and pass the parameter to the Scene
            Scene loginScene = new Scene(loginPane);
                //Load the Scene to Primary Stage
            primaryStage.setScene(loginScene);
            primaryStage.getIcons().add(new Image("/view/Icons/tourIcon.png"));
            primaryStage.setTitle("Login Form");
                //Display the PrimaryStage
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Open Main Window inside the Primary Stage when the login completes
    public void mainWindow(){

        try {

            FXMLLoader mainWindowLoader = new FXMLLoader(Main.class.getResource("/view/MainMenu.fxml"));
            AnchorPane mainMenuPane = mainWindowLoader.load();
            Scene mainScene = new Scene(mainMenuPane);
            MainMenuController openMainMenu = mainWindowLoader.getController();
            openMainMenu.setMain(this);
            primaryStage.setScene(mainScene);
            primaryStage.getIcons().add(new Image("/view/Icons/tourIcon.png"));
            primaryStage.setTitle("Main Menu");
            primaryStage.show();

        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void destinationWindow(){
        try{

            FXMLLoader destinationLoader = new FXMLLoader(Main.class.getResource("/view/DestinationView.fxml"));
            AnchorPane destinationPain = destinationLoader.load();

            DestinationController destinationController = destinationLoader.getController();
            Scene destinationScene = new Scene(destinationPain);
            Stage secondaryStage = new Stage();
            destinationController.setDesignationView(this,secondaryStage);
            secondaryStage.initOwner(primaryStage);
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.setTitle("Destinations");
            secondaryStage.getIcons().add(new Image("/view/Icons/tourIcon.png"));
            secondaryStage.setScene(destinationScene);
            secondaryStage.show();



        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }


}



