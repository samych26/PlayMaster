package com.example.playmaster.Models;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class HistoriqueTournoisModel {
    public static List<Tournoi> getListeTournois() {
        List<Tournoi> tournois = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = BaseDonneeConnexion.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, nom, type, date_debut, date_fin, nombre_participants, frais_inscription, recompense FROM tournoi"

            );

            while (resultSet.next()) {
                int id_tournoi = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String type = resultSet.getString("type");
                LocalDate dateDebut = resultSet.getDate("date_debut").toLocalDate();
                LocalDate dateFin = resultSet.getDate("date_fin").toLocalDate();
                int nombreParticipants = resultSet.getInt("nombre_participants");
                double fraisInscription = resultSet.getDouble("frais_inscription");
                String recompense = resultSet.getString("recompense");


                Tournoi tournoi = new Tournoi(id_tournoi, nom, type, dateDebut, dateFin, nombreParticipants, fraisInscription, recompense);
                tournois.add(tournoi);
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
        return tournois;
    }
    public static List<Participant> getParticipantsByTournoi(int tournoiId) {
        List<Participant> participants = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BaseDonneeConnexion.getConnection();
            String query = "SELECT nom, prenom, age, email FROM Participants WHERE id_tournoi = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tournoiId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");

                Participant participant = new Participant(nom, prenom, age, email);
                participants.add(participant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return participants;
    }
    public static List<Tournoi> searchTournois(String searchText) {
        List<Tournoi> tournois = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = BaseDonneeConnexion.getConnection();
            String query = "SELECT id, nom, type, date_debut, date_fin, nombre_participants, frais_inscription, recompense " +
                    "FROM tournoi WHERE LOWER(nom) LIKE ? OR LOWER(type) LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchText.toLowerCase() + "%");
            preparedStatement.setString(2, "%" + searchText.toLowerCase() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_tournoi = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String type = resultSet.getString("type");
                LocalDate dateDebut = resultSet.getDate("date_debut").toLocalDate();
                LocalDate dateFin = resultSet.getDate("date_fin").toLocalDate();
                int nombreParticipants = resultSet.getInt("nombre_participants");
                double fraisInscription = resultSet.getDouble("frais_inscription");
                String recompense = resultSet.getString("recompense");

                Tournoi tournoi = new Tournoi(id_tournoi, nom, type, dateDebut, dateFin, nombreParticipants, fraisInscription, recompense);
                tournois.add(tournoi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tournois;
    }

}
