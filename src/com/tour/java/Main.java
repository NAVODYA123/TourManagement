package com.tour.java;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application{
    public static void main(String[] args) {launch(args);}
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        primaryStage.setTitle("Tour Management Login");
        primaryStage.getIcons().add(new Image("com/tour/java/Icons/ApplicationImage.png"));
        primaryStage.setScene(new Scene(root,342,221));
        primaryStage.show();
    }
}



