package com.example.playmaster.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnregistrerClientModel {
    public static boolean enregistrerClient(Client client){
        String query = "INSERT INTO client (nomClient, prenomClient, dateDeNaissanceClient, telephoneClient) VALUES (?, ?, ?, ?)";

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
