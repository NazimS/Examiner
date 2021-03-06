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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pojos.User;
import types.UserTypes;

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
    public static User loginedUser;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataManager = new DataManager();
    }

    public void login() {
        User user = new User();
        user.setEmail(username.getText());
        user.setPassword(password.getText());
        user = dataManager.login(user);
        loginedUser = user;
        if (user == null) {
            username.setStyle("-fx-border-color : red");
            password.setStyle("-fx-border-color : red");
            user = new User();
            user.setEmail(username.getText());
            dataManager.countFails(user);
        } else {
            try {
                String destination;
                destination = user.getUserType() == UserTypes.TEACHER ? "/views/Teacher.fxml" : "/views/Student.fxml";
                Parent root = FXMLLoader.load(getClass().getResource(destination));
                username.getScene().setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    @FXML
    public void goToSignup() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Signup.fxml"));
            username.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
