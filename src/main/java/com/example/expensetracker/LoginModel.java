package com.example.expensetracker;

import java.sql.*;

public class LoginModel {
    Connection connection;
    public LoginModel() {
        connection = SQLiteConnection.Connector();
        if(connection == null) System.exit(1);
    }

    public boolean isDBConnected() {
        try {
            return !connection.isClosed();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isLogin(String user, String pass) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from user where username = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(user);
                return true;

            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public String getName(String username) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select name from user where username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString("name");
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getBudget(String username) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select budget from user where username = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return Integer.parseInt(resultSet.getString("budget"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public String getUsername(String user) {
        return user;
    }
}
