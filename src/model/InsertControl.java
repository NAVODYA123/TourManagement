package model;

import controller.Main;
import javafx.scene.control.*;



/**
 * Created by Gayan Kalinga on 07-May-17.
 */
public class InsertControl extends Main {
    private String opName, opEmail, opDOB, opPWD, opAccess;
    private double opSalary;

    public TextField opNameTF, opEmailTF, opSalaryTF, opPWDTF;
    public DatePicker opDOBDP;
    public ComboBox opAccesLevelCB;

    public void insertButtonClicked() {

        opName = opNameTF.getText();
        opEmail = opEmailTF.getText();
        opDOB = opDOBDP.getValue().toString();
        opPWD = opPWDTF.getText();
        opSalary = Double.parseDouble(opSalaryTF.getText());
        opAccess = opAccesLevelCB.getValue().toString();

        //System.out.println(opAccesLevelCB + "--"+opAccess);

        if (opAccess.equals("Administrator")) {
            opAccess = "adm";
        } else {
            opAccess = "opr";
        }

        InsertOperator opr = new InsertOperator();
        boolean success = opr.insertOperator(opName, opSalary, opEmail, opDOB, opPWD, opAccess);

        if(success)handleClearButtonClick();

    }

    public void handleClearButtonClick() {
        opNameTF.setText("");
        opEmailTF.setText("");
        opPWDTF.setText("");
        opSalaryTF.setText("");
        opAccesLevelCB.setValue(null);
        opDOBDP.setValue(null);
    }

}






