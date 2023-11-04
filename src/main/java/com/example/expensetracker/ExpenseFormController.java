package com.example.expensetracker;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;

public class ExpenseFormController implements Initializable {
    @FXML
    public Label paymentMethod;

    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rb3;
    @FXML private RadioButton rb4;


    @FXML
    public ComboBox<String> comboBox;
    ObservableList<String> list = FXCollections.observableArrayList("Essential", "Self-Care", "Non-Essential");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(list);
    }

    public void getPaymentMethod(ActionEvent event) {
        if(rb1.isSelected()) {
            paymentMethod.setText(rb1.getText());
        }
        else if(rb2.isSelected()) {
            paymentMethod.setText(rb2.getText());
        }
        else if(rb3.isSelected()) {
            paymentMethod.setText(rb3.getText());
        }
        else if(rb4.isSelected()) {
            paymentMethod.setText(rb4.getText());
        }
    }
}
