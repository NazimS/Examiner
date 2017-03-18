package controllers;

import db.DataManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ResultController implements Initializable {

    @FXML
    private Button exit;
    @FXML
    private Label telebe, pass;
    @FXML
    private Label netice;
    @FXML
    private Label duzgun;
    @FXML
    private Label sehv;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        duzgun.setText(duzgun.getText() + " : " + StudentController.trueAnswers);
        sehv.setText(sehv.getText() + " : " + StudentController.wrongAnswers);
        double finalResult = round(100.00 * StudentController.trueAnswers / (StudentController.trueAnswers + StudentController.wrongAnswers), 2);
        netice.setText(netice.getText() + " : " + finalResult + " %");
        telebe.setText(telebe.getText() + " : " + loginController.loginedUser.getName() + " " + loginController.loginedUser.getSurname());
        if (finalResult > 50.00) {
            pass.setText(" Imtahandan muveffeqiyyetle  kecdiniz ");
        } else {
            pass.setTextFill(Color.RED);
            pass.setText(" Imtahandan kesildiniz ");
        }
    }

    public void goToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            telebe.getScene().setRoot(root);
            System.out.println("#############################");
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
