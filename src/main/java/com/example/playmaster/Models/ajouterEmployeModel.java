package com.example.playmaster.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ajouterEmployeModel {
    public static boolean ajouterEmploye(Employe employe) {

        String query = "INSERT INTO employe (nomEmploye, prenomEmploye, dateDeNaissance, telephone, poste, salaire, dateEmbauche, statut) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = BaseDonneeConnexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, employe.getNomEmploye());
            statement.setString(2, employe.getPrenomEmploye());
            statement.setDate(3, java.sql.Date.valueOf(employe.getDateDeNaissance()));
            statement.setString(4, employe.getTelephone());
            statement.setString(5, employe.getPoste());
            statement.setDouble(6, employe.getSalaire());
            statement.setDate(7, java.sql.Date.valueOf(employe.getDateEmbauche()));
            statement.setString(8, employe.getStatut());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Si l'insertion a réussi, retourne true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // En cas d'erreur
        }}
}
