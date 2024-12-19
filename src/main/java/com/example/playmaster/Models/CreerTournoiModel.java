package com.example.playmaster.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreerTournoiModel {
    public static boolean creerTournoi(String nomTournoi, String typeTournoi, LocalDate dateDebut, LocalDate dateFin,
                                       int nombreParticipants, double fraisInscription, String recompense) {
        // Convertir LocalDate en String au format "yyyy-MM-dd"
        String dateDebutStr = dateDebut != null ? dateDebut.toString() : null;
        String dateFinStr = dateFin != null ? dateFin.toString() : null;

        // Requête SQL d'insertion
        String query = "INSERT INTO tournoi (nom, type, date_debut, date_fin, nombre_participants, frais_inscription, recompense) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = BaseDonneeConnexion.getConnection();  // Connexion à la base de données
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Remplir les paramètres de la requête
            statement.setString(1, nomTournoi);
            statement.setString(2, typeTournoi);
            statement.setString(3, dateDebutStr);  // Utilisation de la conversion en String
            statement.setString(4, dateFinStr);    // Utilisation de la conversion en String
            statement.setInt(5, nombreParticipants);
            statement.setDouble(6, fraisInscription);
            statement.setString(7, recompense);

            // Exécution de la requête
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Retourner true si l'insertion a réussi
        } catch (SQLException e) {
            e.printStackTrace();  // Imprimer l'erreur dans la console
            return false;  // Retourner false si une erreur se produit
        }
    }
}
