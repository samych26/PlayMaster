package com.example.playmaster.Models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDonneeConnexion {
    private static final String URL = "jdbc:mysql://localhost:3306/playMaster";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur de connexion à la base de données");
        }
    }
}
