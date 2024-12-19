package com.example.playmaster.Models;

import java.sql.*;
import java.time.LocalDateTime;


public class LoginModel {
    public static String identifiantConnecte; // Nom et prénom pour employé, username pour admin

    public static boolean validationCompte(String id, String motdepasse, String accountType) {
        try (Connection connection = BaseDonneeConnexion.getConnection()) {
            if (accountType.equals("Admin")) {
                // Vérification pour l'admin
                String sql = "SELECT * FROM admin WHERE username = ? AND mot_de_passe = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, id);
                    stmt.setString(2, motdepasse);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        // Récupérer le username pour l'admin
                        identifiantConnecte = rs.getString("username");
                        return true; // Les identifiants sont valides pour un admin
                    }
                }
            } else if (accountType.equals("Employé")) {
                // Découper l'identifiant pour extraire nom et prénom
                String[] parts = id.split("(?<=\\D)(?=\\p{Upper})");
                if (parts.length < 2) {
                    return false;
                }
                String nomEmploye = parts[0];
                String prenomEmploye = parts[1];

                // Vérification pour l'employé
                String sql = "SELECT id, nomEmploye, prenomEmploye, tentatives, compte_bloque FROM employe WHERE nomEmploye = ? AND prenomEmploye = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, nomEmploye);
                    stmt.setString(2, prenomEmploye);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        // Récupérer le nom et prénom pour l'employé
                        identifiantConnecte = nomEmploye + " " + prenomEmploye;
                        int employeId = rs.getInt("id");
                        int tentatives = rs.getInt("tentatives");
                        boolean compteBloque = rs.getBoolean("compte_bloque");

                        if (compteBloque) {
                            return false;
                        }

                        String motDePasseEmploye = creerMotDePasseEmploye(nomEmploye, prenomEmploye, employeId);
                        if (motDePasseEmploye.equals(motdepasse)) {
                            resetFailedAttempts(employeId, connection);
                            return true;
                        } else {
                            incrementFailedAttempts(employeId, connection, tentatives);
                            return false;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String creerMotDePasseEmploye(String nomEmploye, String prenomEmploye, int employeId) {
        return nomEmploye + prenomEmploye + employeId;
    }

    // Méthode pour réinitialiser les tentatives après une connexion réussie
    private static void resetFailedAttempts(int employeId, Connection connection) throws SQLException {
        String sql = "UPDATE employe SET tentatives = 0 WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeId);
            stmt.executeUpdate();
        }
    }

    // Méthode pour incrémenter le nombre de tentatives échouées et bloquer le compte après 5 tentatives
    private static void incrementFailedAttempts(int employeId, Connection connection, int tentatives) throws SQLException {
        if (tentatives + 1 >= 5) {
            // Bloquer le compte après 5 tentatives échouées
            String sql = "UPDATE employe SET tentatives = ?, compte_bloque = ?, date_blocage = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, 0); // Réinitialiser les tentatives
                stmt.setBoolean(2, true); // Marquer le compte comme bloqué
                stmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); // Date et heure du blocage
                stmt.setInt(4, employeId);
                stmt.executeUpdate();
            }

            // Ajouter le compte bloqué dans la table comptebloquer
            ajouterCompteBloque(employeId, connection);
        } else {
            // Sinon, incrémenter simplement le nombre de tentatives échouées
            String sql = "UPDATE employe SET tentatives = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, tentatives + 1);
                stmt.setInt(2, employeId);
                stmt.executeUpdate();
            }
        }
    }
    private static void ajouterCompteBloque(int employeId, Connection connection) throws SQLException {
        // Récupérer les informations de l'employé
        String selectSql = "SELECT nomEmploye, prenomEmploye, date_blocage FROM employe WHERE id = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
            selectStmt.setInt(1, employeId);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    String nomEmploye = rs.getString("nomEmploye");
                    String prenomEmploye = rs.getString("prenomEmploye");
                    Timestamp dateBlocage = rs.getTimestamp("date_blocage");

                    // Insérer dans la table comptebloquer
                    String insertSql = "INSERT INTO comptebloquer (username, mot_de_passe, date_bloquage, raison_bloquage) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                        String motDePasse = creerMotDePasseEmploye(nomEmploye, prenomEmploye, employeId);
                        insertStmt.setString(1, nomEmploye + prenomEmploye); // Identifiant
                        insertStmt.setString(2, motDePasse); // Mot de passe
                        insertStmt.setTimestamp(3, dateBlocage); // Date de blocage
                        insertStmt.setString(4, "Trop de tentatives échouées"); // Raison du blocage
                        insertStmt.executeUpdate();
                    }
                }
            }
        }
    }


    public static boolean isCompteBloque(String id, String accountType) throws SQLException {
        try (Connection connection = BaseDonneeConnexion.getConnection()) {
            if (accountType.equals("Employé")) {
                // Découpage de l'identifiant pour extraire nom et prénom
                String[] parts = id.split("(?<=\\D)(?=\\p{Upper})");
                if (parts.length < 2) {
                    return false;
                }
                String nomEmploye = parts[0];
                String prenomEmploye = parts[1];

                // Vérification dans la table employé si le compte est bloqué
                String sql = "SELECT compte_bloque FROM employe WHERE nomEmploye = ? AND prenomEmploye = ?";
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, nomEmploye);
                    stmt.setString(2, prenomEmploye);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        return rs.getBoolean("compte_bloque"); // Retourne vrai si le compte est bloqué
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Méthode pour débloquer un compte d'employé
    public static boolean debloquerCompte(String id, String motdepasse) throws SQLException {
        try (Connection connection = BaseDonneeConnexion.getConnection()) {
            // Découpage de l'identifiant pour extraire nom et prénom
            String[] parts = id.split("(?<=\\D)(?=\\p{Upper})");
            if (parts.length < 2) {
                System.out.println("Format de l'identifiant invalide : " + id);
                return false;
            }

            String nomEmploye = parts[0];
            String prenomEmploye = parts[1];

            // Vérification dans la table employé
            String sql = "SELECT id, compte_bloque FROM employe WHERE nomEmploye = ? AND prenomEmploye = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, nomEmploye);
                stmt.setString(2, prenomEmploye);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    int employeId = rs.getInt("id");
                    boolean compteBloque = rs.getBoolean("compte_bloque");

                    // Génération du mot de passe attendu
                    String motDePasseAttendu = creerMotDePasseEmploye(nomEmploye, prenomEmploye, employeId);

                    // Vérification du mot de passe et du statut du compte
                    if (compteBloque && motDePasseAttendu.equals(motdepasse)) {
                        // Débloquer le compte
                        String sqlDeblocage = "UPDATE employe SET compte_bloque = FALSE, tentatives = 0 WHERE id = ?";
                        try (PreparedStatement stmtDeblocage = connection.prepareStatement(sqlDeblocage)) {
                            stmtDeblocage.setInt(1, employeId);
                            int rowsUpdated = stmtDeblocage.executeUpdate();
                            return rowsUpdated > 0;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}






