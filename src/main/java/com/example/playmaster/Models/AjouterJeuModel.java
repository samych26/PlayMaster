package com.example.playmaster.Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjouterJeuModel {
    public static boolean ajouterJeu(String nom, String type, String emplacement, String disponibilite, double prix) {
        String query ="INSERT INTO jeu (nom, typejeu, emplacement, disponibilite, prixparsession) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = BaseDonneeConnexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            statement.setString(2, type);
            statement.setString(3, emplacement);
            statement.setString(4, disponibilite);
            statement.setDouble(5, prix);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Retourne true si l'insertion a réussi
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Retourne false si une erreur se produit
        }
    }
}
