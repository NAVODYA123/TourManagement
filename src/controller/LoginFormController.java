package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.LoginValidation;

/**
 * Created by Gayan Kalinga on 09-May-17.
 */

public class LoginFormController {
    @FXML private TextField userNameText;
    @FXML private TextField passwordText;
    @FXML private Button loginButton;
    @FXML private Label remarkLabel;


    private Main main;



    public void setMain(Main main){
        this.main=main;
    }


    public void validateLogin (){
        String uid = userNameText.getText();
        String pwd = passwordText.getText();

        if(uid.equals("")){
            Alert alertP = new Alert(Alert.AlertType.ERROR);
            alertP.setTitle("Empty User ID");
            alertP.setHeaderText("Please Enter User ID");
            alertP.setContentText("You must enter Valid User ID to proceed! ");
            alertP.showAndWait();
        }else if(pwd.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Password");
            alert.setHeaderText("Please Enter Password");
            alert.setContentText("You must enter Valid Password to proceed! ");
            alert.showAndWait();
        }

        LoginValidation login = new LoginValidation();
        String resultLogin=login.userValidation(uid,pwd);

        remarkLabel.setText(resultLogin);
        if(resultLogin.equals("ValidLogin")){
            //System.out.println("Open Next Form ");
            changeWindow();
        }
    }

    public void changeWindow(){
        main.mainWindow();
    }


}
