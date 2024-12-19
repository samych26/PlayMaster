package com.example.playmaster.Models;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class SupprimerEmployeModel {
    public static boolean supprimerEmploye(String nom, String prenom, LocalDate dateNaissance) {
        String query = "DELETE FROM employe WHERE nomEmploye = ? AND prenomEmploye = ? AND dateDeNaissance = ?";

        try (Connection connection = BaseDonneeConnexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setDate(3, java.sql.Date.valueOf(dateNaissance));

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
