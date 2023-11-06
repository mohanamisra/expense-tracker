package com.example.expensetracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExpenseFormController implements Initializable {
    Connection connection;
    public boolean update;

    public ExpenseFormController() {
        update = false;
        connection = SQLiteConnection.Connector();
        if(connection == null) System.exit(1);
    }
    LoginModel loginModel = new LoginModel();
    @FXML
    public Label paymentMethod;

    @FXML private RadioButton rb1;
    @FXML private RadioButton rb2;
    @FXML private RadioButton rb3;
    @FXML private RadioButton rb4;

    @FXML public Button addExpense;
    @FXML public TextField expenseCost;


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

    public void insertExpense(ActionEvent event) {
        update = true;
        String category = "\"" + comboBox.getValue().toString() + "\"";
        int newValue = Integer.parseInt(expenseCost.getText());

        String query = "SELECT " + category + " FROM user WHERE username = ?";
        try {
            PreparedStatement selectStatement = connection.prepareStatement(query);
            selectStatement.setString(1, "mohanamisra"); // Replace with the actual username

            ResultSet resultSet = selectStatement.executeQuery();

            int oldValue = 0;

            if (resultSet.next()) {
                oldValue = resultSet.getInt(1); // Get the current value from the database
            }

            // Calculate the new total
            int totalValue = oldValue + newValue;

            // Update the database with the new total
            String updateQuery = "UPDATE user SET " + category + " = ? WHERE username = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setInt(1, totalValue);
            updateStatement.setString(2, "mohanamisra"); // Replace with the actual username
            updateStatement.executeUpdate();

            // Clean up
            selectStatement.close();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
