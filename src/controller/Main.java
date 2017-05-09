package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application{

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage=primaryStage;
        mainWindow();
    }

    public void mainWindow(){
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


    public static void main(String[] args) {launch(args);}
}



