package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String LOGIN = "root1";
    private static final String PASSWORD = "root1";
    Connection connection = null;

    public Util() {
    }

    public Connection connect() {
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, LOGIN, PASSWORD);
            //System.out.println("Соединение с базой данных установлено!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close() {
        try {
            connection.close();
            //System.out.println("Соединение с базой данных разорвано!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


