package com.example.playmaster.Models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ListeEmployeModel {

    public static List<Employe> getListeEmploye() {
        List<Employe> employes = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = BaseDonneeConnexion.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT nomEmploye, prenomEmploye, dateDeNaissance, telephone, poste, salaire, dateEmbauche, statut FROM employe"
            );

            while (resultSet.next()) {
                String nomEmploye = resultSet.getString("nomEmploye");
                String prenomEmploye = resultSet.getString("prenomEmploye");
                LocalDate dateDeNaissance = resultSet.getDate("dateDeNaissance").toLocalDate();
                String telephone = resultSet.getString("telephone");
                String poste = resultSet.getString("poste");
                double salaire = resultSet.getDouble("salaire");
                LocalDate dateEmbauche = resultSet.getDate("dateEmbauche").toLocalDate();
                String statut = resultSet.getString("statut");

                // Créer un objet Employe avec les bonnes valeurs
                Employe employe = new Employe(nomEmploye, prenomEmploye, dateDeNaissance, telephone, poste, salaire, dateEmbauche, statut);
                employes.add(employe);
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
        return employes;
    }

    public static List<Employe> rechercherEmploye(String searchText) {
        List<Employe> employes = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = BaseDonneeConnexion.getConnection();
            statement = connection.createStatement();

            String query = "SELECT * FROM Employe WHERE "
                    + "nomEmploye LIKE '%" + searchText + "%' OR "
                    + "prenomEmploye LIKE '%" + searchText + "%' OR "
                    + "telephone LIKE '%" + searchText + "%' OR "
                    + "poste LIKE '%" + searchText + "%'";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Employe employe = new Employe(
                        resultSet.getString("nomEmploye"),
                        resultSet.getString("prenomEmploye"),
                        resultSet.getDate("dateDeNaissance").toLocalDate(),
                        resultSet.getString("telephone"),
                        resultSet.getString("poste"),
                        resultSet.getDouble("salaire"),
                        resultSet.getDate("dateEmbauche").toLocalDate(),
                        resultSet.getString("statut")
                );
                employes.add(employe);
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
        return employes;
    }
}
