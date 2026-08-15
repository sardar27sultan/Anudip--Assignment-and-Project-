package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DBConnection;

public class LoginDAO {

    private static final String LOGIN =
            "SELECT * FROM users WHERE username = ? AND password = ?";

    public boolean login(String username, String password) {

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(LOGIN);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
}