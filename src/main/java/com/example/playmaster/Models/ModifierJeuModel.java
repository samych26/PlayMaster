package com.example.playmaster.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifierJeuModel {
    public static boolean modifierJeu(String nomJeu, String typeJeu, String emplacement, String disponibilite, double prixSession) {
        boolean estMisAJour = false;
        Connection connexion = null;
        PreparedStatement requete = null;

        try {
            // Connexion à la base de données
            connexion = BaseDonneeConnexion.getConnection();

            // Construction de la requête SQL dynamique
            StringBuilder requeteSQL = new StringBuilder("UPDATE Jeu SET ");
            boolean aDesModifications = false;

            // Ajout des champs modifiables à la requête SQL
            if (!typeJeu.isEmpty()) {
                requeteSQL.append("typejeu = ?, ");
                aDesModifications = true;
            }
            if (!emplacement.isEmpty()) {
                requeteSQL.append("emplacement = ?, ");
                aDesModifications = true;
            }
            if (!disponibilite.isEmpty()) {
                requeteSQL.append("disponibilite = ?, ");
                aDesModifications = true;
            }
            if (prixSession >= 0) {
                requeteSQL.append("prixparsession = ?, ");
                aDesModifications = true;
            }

            // Vérification qu'il y a des modifications
            if (!aDesModifications) {
                return false; // Aucun champ à modifier
            }

            // Supprimer la dernière virgule et ajouter la condition WHERE
            requeteSQL.setLength(requeteSQL.length() - 2);
            requeteSQL.append(" WHERE nom = ?");

            // Préparer la requête
            requete = connexion.prepareStatement(requeteSQL.toString());

            // Remplir les paramètres de la requête
            int indexParametre = 1;
            if (!typeJeu.isEmpty()) {
                requete.setString(indexParametre++, typeJeu);
            }
            if (!emplacement.isEmpty()) {
                requete.setString(indexParametre++, emplacement);
            }
            if (!disponibilite.isEmpty()) {
                requete.setString(indexParametre++, disponibilite);
            }
            if (prixSession >= 0) {
                requete.setDouble(indexParametre++, prixSession);
            }
            requete.setString(indexParametre, nomJeu);

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
