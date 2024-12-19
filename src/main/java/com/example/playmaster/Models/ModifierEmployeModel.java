package com.example.playmaster.Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ModifierEmployeModel {

    public static boolean modifierEmploye(String nom, String prenom, LocalDate dateNaissance, String numeroTelephone,
                                          String poste, double salaire, String statut) {
        boolean estMisAJour = false;
        Connection connexion = null;
        PreparedStatement requete = null;

        try {
            // Connexion à la base de données
            connexion = BaseDonneeConnexion.getConnection();

            // Construction de la requête SQL dynamique
            StringBuilder requeteSQL = new StringBuilder("UPDATE Employe SET ");
            boolean aDesModifications = false;

            // Ajout des champs modifiables à la requête SQL
            if (!numeroTelephone.isEmpty()) {
                requeteSQL.append("telephone = ?, ");
                aDesModifications = true;
            }
            if (!poste.isEmpty()) {
                requeteSQL.append("poste = ?, ");
                aDesModifications = true;
            }
            if (!statut.isEmpty()) {
                requeteSQL.append("statut = ?, ");
                aDesModifications = true;
            }
            if (salaire >= 0) {
                requeteSQL.append("salaire = ?, ");
                aDesModifications = true;
            }

            // Vérification qu'il y a des modifications
            if (!aDesModifications) {
                return false; // Aucun champ à modifier
            }

            // Supprimer la dernière virgule et ajouter la condition WHERE
            requeteSQL.setLength(requeteSQL.length() - 2);
            requeteSQL.append(" WHERE nomEmploye = ? AND prenomEmploye = ? AND dateDeNaissance = ?");

            // Préparer la requête
            requete = connexion.prepareStatement(requeteSQL.toString());

            // Remplir les paramètres de la requête
            int indexParametre = 1;
            if (!numeroTelephone.isEmpty()) {
                requete.setString(indexParametre++, numeroTelephone);
            }
            if (!poste.isEmpty()) {
                requete.setString(indexParametre++, poste);
            }
            if (!statut.isEmpty()) {
                requete.setString(indexParametre++, statut);
            }
            if (salaire >= 0) {
                requete.setDouble(indexParametre++, salaire);
            }

            requete.setString(indexParametre++, nom);
            requete.setString(indexParametre++, prenom);
            requete.setDate(indexParametre, java.sql.Date.valueOf(dateNaissance));

            // Exécution de la mise à jour
            int lignesModifiees = requete.executeUpdate();
            estMisAJour = lignesModifiees > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (requete != null) {
                    requete.close();
                }
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return estMisAJour;
    }
}
