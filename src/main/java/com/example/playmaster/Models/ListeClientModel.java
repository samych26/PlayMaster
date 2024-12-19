package com.example.playmaster.Models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListeClientModel {

    public static List<Client> getListeClients() {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = BaseDonneeConnexion.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nomClient, prenomClient, dateDeNaissanceClient, telephoneClient FROM Client");

            while (resultSet.next()) {
                String nom = resultSet.getString("nomClient");
                String prenom = resultSet.getString("prenomClient");
                LocalDate dateDeNaissance = resultSet.getDate("dateDeNaissanceClient").toLocalDate();
                String telephone = resultSet.getString("telephoneClient");

                Client client = new Client(nom, prenom, dateDeNaissance, telephone);
                clients.add(client);
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
        return clients;
    }
}
