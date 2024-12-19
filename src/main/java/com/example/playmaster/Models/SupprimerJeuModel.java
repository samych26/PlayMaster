package com.example.playmaster.Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class SupprimerJeuModel {
    public static boolean supprimerJeu(String nomJeu, String emplacement) {
        String query = "DELETE FROM jeu WHERE nom = ? AND emplacement = ?";

        try (Connection connection = BaseDonneeConnexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nomJeu);
            statement.setString(2, emplacement);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
