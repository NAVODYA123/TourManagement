package controller;

import javafx.fxml.FXML;
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
        LoginValidation login = new LoginValidation();
        String resultLogin=login.userValidation(uid,pwd);

        remarkLabel.setText(resultLogin);
        if(resultLogin.equals("ValidLogin")){
            System.out.println("Open Next Form ");
        }
    }


}
