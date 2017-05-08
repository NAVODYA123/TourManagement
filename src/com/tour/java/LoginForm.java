package com.tour.java;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.sql.PreparedStatement;


/**
 * Created by Gayan Kalinga on 07-May-17.
 */

public class LoginForm extends Main {

    public PasswordField passwordField;
    public TextField userIDTextField;
    public Label validationLabel;
    public Button signInButton;


    public void signInButtonClicked() {

        String uid = userIDTextField.getText();
        String pwd = passwordField.getText();
        boolean logged = false;
        System.out.println(uid);
        System.out.println(pwd);


        LoginValidation validation = new LoginValidation();
        int loginStatusFromValidation = validation.loginCheck(uid, pwd);

        System.out.println(loginStatusFromValidation);

        if (loginStatusFromValidation == 3) {
            signInButton.setDisable(true);
            logged = false;
        } else if (loginStatusFromValidation == 1) {
            handle();
        } else {
            logged = false;
        }


    }

    public void handle() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("com/tour/java/MainMenu.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Main Menu");
            stage.getIcons().add(new Image("com/tour/java/Icons/ApplicationImage.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root, 600, 450));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



