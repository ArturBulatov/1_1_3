package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public Util() {
    }
    public static void createUsersTable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysql";
        String login = "root1";
        String password = "root1";
        String sqlCommand = "CREATE TABLE if not exists UsersTable(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)";
        try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sqlCommand);
            System.out.println("Таблица создана.");
        }
    }
    public static void dropUsersTable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysql";
        String login = "root1";
        String password = "root1";
        String sqlCommand = "DROP TABLE if exists UsersTable";
        try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement()){
            stmt.executeUpdate(sqlCommand);
        }
    }
    public static void saveUser(String n, String l, byte a) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysql";
        String login = "root1";
        String password = "root1";
        try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement()) {
            String sql = "INSERT INTO UsersTable (name, lastName, age) Values (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, n);
            preparedStatement.setString(2, l);
            preparedStatement.setByte(3, a);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + n + " добавлен в базу данных.");
        }
    }
    public static List<User> getAllUsers(List<User> listUsers) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysql";
        String login = "root1";
        String password = "root1";
        try (Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM UsersTable");
            while (rs.next()) {
                listUsers.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3),rs.getByte(4)));
            }
        }
        return listUsers;
    }
    public static void cleanUsersTable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysql";
        String login = "root1";
        String password = "root1";
        try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DELETE FROM UsersTable");
            System.out.println("Таблица удалена.");
        }
    }
    public static void removeUserById(long id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mysql";
        String login = "root1";
        String password = "root1";
        try(Connection con = DriverManager.getConnection(url, login, password); Statement stmt = con.createStatement()) {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM UsersTable WHERE Id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User с номером id = " + id + " удален из базы данных.");
        }
    }
}


