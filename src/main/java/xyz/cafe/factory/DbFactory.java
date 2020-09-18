package xyz.cafe.factory;

import xyz.cafe.common.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbFactory {

    public static Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

}