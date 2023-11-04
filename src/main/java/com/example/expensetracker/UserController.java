package com.example.expensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private Label name;
    @FXML
    private Label budget;

    public void getUser(String user) {
        name.setText(user);
    }

    public void addExpense(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Pane root = FXMLLoader.load(getClass().getResource("expense_form.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Expense Form");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setBudget(String user) {budget.setText(user);}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
