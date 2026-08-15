package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/gym_management";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "root";

    private static Connection connection;

    private DBConnection() {
    }

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );

                //System.out.println("Connected Successfully");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return connection;

    }

}