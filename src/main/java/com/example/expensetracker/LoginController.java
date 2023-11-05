package com.example.expensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();

    @FXML
    private Label isConnected;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(loginModel.isDBConnected()) {
            isConnected.setText("PLEASE LOGIN");
        }
        else {
            isConnected.setText("CONNECTION FAILED");
        }
    }

    public void login(ActionEvent event) {
        try {
            if (loginModel.isLogin(username.getText(), password.getText())) {
                isConnected.setText("LOGIN SUCCESSFUL");
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("user.fxml").openStream());

                String name = loginModel.getName(username.getText());
                UserController userController = (UserController)loader.getController();
                userController.getUser("Hello, " + name);

                int budget = loginModel.getBudget(username.getText());
                userController.setBudget("₹" + String.valueOf(budget));
                Scene scene = new Scene(root);
                stage.setTitle("Display Screen");
                stage.setScene(scene);
                stage.show();
            }
            else
                isConnected.setText("LOGIN FAILED");
        }
        catch(Exception e) {
            isConnected.setText("LOGIN FAILED");
            e.printStackTrace();
        }
    }
}