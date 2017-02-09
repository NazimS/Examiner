/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pojos.User;
import types.UserTypes;
import utilit.Validator;

/**
 * FXML Controller class
 *
 * @author Hp-Pc
 */
public class SignupController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private ComboBox types;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmpassword;
    @FXML
    private Button signup;

    @FXML
    private Label warning;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataManager = new DataManager();
        types.getItems().addAll("Teacher", "Student");
    }

    @FXML
    public void signup() {

        boolean filled = name.getText() != null && surname.getText() != null && email.getText() != null && password.getText() != null && types.getValue() != null;
        if (filled) {
            if (password.getText().equals(confirmpassword.getText()) && Validator.isValidPassword(password.getText())) {
                //signup process
                User user = new User();
                user.setName(name.getText());
                user.setSurname(surname.getText());
                user.setEmail(email.getText());
                user.setUserType(UserTypes.parseFrom(types.getValue().toString()));
                user.setPassword(password.getText());
                boolean result = dataManager.signUpUser(user);
                System.out.println(result + "****************************************************************");
                if (result) {
                    //go to login page again
                    goTologin();
                } else {
                    //message failure
                    email.setStyle("-fx-border-color : red ");
                }
            } else {
                //message that password and confirm password is not equal
                System.out.println("Pasword doenst matchs ************************************************");
                warning.setText("Password doesnt match or doesnt pay requirement");
            }
        } else {
            // whether any field is empty , message and tell user
            if (name.getText() == null) {
                name.setStyle("-fx-border-color : red ");
            }
            if (surname.getText() == null) {
                name.setStyle(" -fx-border-color : red ");
            }
            if (email.getText() == null) {
                email.setStyle(" -fx-border-color : red ");
            }
            if (password.getText() == null) {
                password.setStyle(" -fx-border-color : red ");
            }
            if (confirmpassword.getText() == null) {
                confirmpassword.setStyle(" -fx-border-color : red ");
            }
            if (types.getValue() == null) {
                types.setStyle("-fx-border-color : red ");
            }
        }

    }

    public void goTologin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            name.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }

    }
}
