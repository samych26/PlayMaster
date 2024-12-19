package com.example.playmaster.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupprimerClientModel {

    public static boolean supprimerClient(Client client) {

        String query = "DELETE FROM client WHERE nomClient = ? AND prenomClient = ? AND dateDeNaissanceClient = ? AND telephoneClient = ?";

        try (Connection connection = BaseDonneeConnexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, client.getNomClient());
            statement.setString(2, client.getPrenomClient());
            statement.setDate(3, java.sql.Date.valueOf(client.getDateDeNaissanceClient()));
            statement.setString(4, client.getTelephoneClient());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Si l'insertion a réussi, retourne true
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // En cas d'erreur
        }}
}
