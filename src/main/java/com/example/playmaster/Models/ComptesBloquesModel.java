package com.example.playmaster.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComptesBloquesModel {

    public static List<CompteBloque> getComptesBloques() throws SQLException {
        List<CompteBloque> comptesBloques = new ArrayList<>();

        // Requête pour récupérer les comptes bloqués
        String sql = "SELECT username, mot_de_passe, date_bloquage, raison_bloquage FROM comptebloquer";

        try (Connection connection = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Récupération des données de la table CompteBloquer
                String identifiant = rs.getString("username");
                String motDePasse = rs.getString("mot_de_passe");
                String dateBlocage = rs.getString("date_bloquage");
                String raisonBlocage = rs.getString("raison_bloquage");

                // Création d'un objet CompteBloque
                comptesBloques.add(new CompteBloque(identifiant, motDePasse, dateBlocage, raisonBlocage));
            }
        }

        return comptesBloques;
    }

    public static ObservableList<CompteBloque> rechercherCompteBloque(String searchTerm) {
        ObservableList<CompteBloque> filteredComptes = FXCollections.observableArrayList();

        if (searchTerm.trim().isEmpty()) {
            return filteredComptes;  // Retourner une liste vide si aucun terme n'est saisi
        }

        // Requête SQL pour filtrer les comptes bloqués par identifiant ou raison
        String sql = "SELECT username, mot_de_passe, date_bloquage, raison_bloquage FROM comptebloquer " +
                "WHERE username LIKE ? OR raison_bloquage LIKE ?";

        try (Connection connection = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String identifiant = rs.getString("username");
                    String motDePasse = rs.getString("mot_de_passe");
                    String dateBlocage = rs.getString("date_bloquage");
                    String raisonBlocage = rs.getString("raison_bloquage");

                    filteredComptes.add(new CompteBloque(identifiant, motDePasse, dateBlocage, raisonBlocage));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredComptes;
    }
}
