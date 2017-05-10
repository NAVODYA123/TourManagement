package controller;

import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by Gayan Kalinga on 10-May-17.
 */

public class MainMenuController {

    private Main main;


    public void setMain(Main main){this.main=main;}

    public void setDesignationView(){
        main.destinationWindow();
    }
}
