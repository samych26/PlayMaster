package com.example.playmaster.Models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListeJeuModel {

    public static List<Jeu> getListeJeu() throws SQLException {
        List<Jeu> jeux = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = BaseDonneeConnexion.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nom, typejeu, emplacement, disponibilite , prixparsession FROM jeu");

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String typeJeu = resultSet.getString("typeJeu");
                String emplacement = resultSet.getString("emplacement");
                String disponibilite = resultSet.getString("disponibilite");
                String prixParSession = resultSet.getString("prixParSession");

                // Créer un objet Jeu avec les bonnes valeurs
                Jeu jeu = new Jeu(nom, typeJeu, emplacement, disponibilite, Double.parseDouble(prixParSession));
                jeux.add(jeu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jeux;
    }
    public static ObservableList<Jeu> rechercherJeu(String searchTerm) {
        ObservableList<Jeu> filteredJeux = FXCollections.observableArrayList();

        if (searchTerm.trim().isEmpty()) {
            return filteredJeux;  // Retourner une liste vide si aucun terme n'est saisi
        }

        // Requête SQL pour filtrer les jeux par nom ou type
        String sql = "SELECT nom, typejeu, emplacement, disponibilite, prixparsession FROM jeu " +
                "WHERE nom LIKE ? OR typejeu LIKE ?";

        try (Connection connection = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nom = rs.getString("nom");
                    String typeJeu = rs.getString("typejeu");
                    String emplacement = rs.getString("emplacement");
                    String disponibilite = rs.getString("disponibilite");
                    double prixSession = rs.getDouble("prixparsession");

                    filteredJeux.add(new Jeu(nom, typeJeu, emplacement, disponibilite, prixSession));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredJeux;
    }
}

