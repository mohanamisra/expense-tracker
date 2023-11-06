package com.example.expensetracker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    LoginController loginController = new LoginController();
    ExpenseFormController expenseFormController = new ExpenseFormController();
    @FXML
    public PieChart pieChart;
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
        // Initialize variables
        int essential = 0;
        int nonessential = 0;
        int selfcare = 0;
        int budget = 0;
        int amountLeft = 0;

        // Retrieve data from the database
        try {
            Connection connection = SQLiteConnection.Connector(); // Initialize your database connection

            // Execute SQL queries to retrieve the values from the 'user' table
            String query = "SELECT essential, \"non-essential\", \"self-care\", budget FROM user WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            String username = loginController.getLoggedInUser();
            preparedStatement.setString(1, "mohanamisra"); // Use the username of the currently logged-in user

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                essential = resultSet.getInt("essential");
                nonessential = resultSet.getInt("non-essential");
                selfcare = resultSet.getInt("self-care");
                budget = resultSet.getInt("budget");

                // Calculate the amount left
                amountLeft = budget - (essential + nonessential + selfcare);
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create PieChart data, including the "Amount Left" segment
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Essential", essential),
                new PieChart.Data("Non-Essential", nonessential),
                new PieChart.Data("Self-Care", selfcare),
                new PieChart.Data("Amount Left", amountLeft)
        );

        pieChart.setData(pieChartData);

    }
}
