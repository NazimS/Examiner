package controllers;

import db.DataManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pojos.User;

public class loginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Button login;
    @FXML
    private Button signup;
    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataManager = new DataManager();
    }

    public void login() {
        User user = new User();
        user.setEmail(username.getText());
        user.setPassword(password.getText());
        user = dataManager.login(user);
        if (user == null) {
            username.setStyle("-fx-border-color : red");
            password.setStyle("-fx-border-color : red");
            user = new User();
            user.setEmail(username.getText());
            dataManager.countFails(user);
        } else {
            System.out.println("**********************************************************");
        }
    }

    public void signup() {

    }
}
