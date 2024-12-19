package com.example.playmaster.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnregistrerParticipantModel {

    public static int getNombreParticipantsMax(int tournoiId) throws SQLException {
        try (Connection connection = BaseDonneeConnexion.getConnection()) {
            String query = "SELECT nombre_participants FROM tournoi WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tournoiId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return rs.getInt("nombre_participants");
                }
            }
        }
        return 0;
    }
    public static int getNombreParticipantsActuels() throws SQLException {
        try (Connection connection = BaseDonneeConnexion.getConnection()) {
            String query = "SELECT COUNT(*) AS total FROM Participants";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }
    public static boolean enregistrerParticipant(String nom, String prenom, int age, String email, int id_tournoi) throws SQLException {
        try (Connection conn = BaseDonneeConnexion.getConnection()) {
            String query = "INSERT INTO Participants (nom, prenom, age, email, id_tournoi) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                stmt.setInt(3, age);
                stmt.setString(4, email);
                stmt.setInt(5, id_tournoi);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        }
    }
}
