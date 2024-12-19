package com.example.playmaster.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ViewController {

    @FXML
    private BorderPane contentPane;

    @FXML
    private Label welcomeLabel; // Assurez-vous que le fx:id correspond à celui du fichier FXML

    // Méthode pour définir le message de bienvenue
    public void setWelcomeMessage(String message) {
        if (welcomeLabel != null) {
            welcomeLabel.setText(message);
        } else {
            System.out.println("Erreur : welcomeLabel est null.");
        }
    }

    @FXML
    private void chargerPageCliquer(ActionEvent event) {
        String fxmlFile = "";

        // Déterminer l'origine de l'événement
        Object source = event.getSource();

        if (source instanceof MenuItem) {
            // Si l'événement provient d'un MenuItem
            MenuItem clickedMenuItem = (MenuItem) source;
            fxmlFile = determineFXMLFile(clickedMenuItem.getId());
        } else if (source instanceof Button) {
            // Si l'événement provient d'un Button
            Button clickedButton = (Button) source;
            fxmlFile = determineFXMLFile(clickedButton.getId());
        } else {
            System.err.println("Événement déclenché par un objet non pris en charge : " + source.getClass().getName());
            return;
        }

        // Charger la page FXML associée
        if (!fxmlFile.isEmpty()) {
            try {
                java.net.URL fxmlLocation = getClass().getResource(fxmlFile);
                if (fxmlLocation == null) {
                    System.err.println("Fichier FXML introuvable : " + fxmlFile);
                    return;
                }
                Parent content = FXMLLoader.load(fxmlLocation);

                if (fxmlFile.equals("/Fxml/Login.fxml")) {
                    // Si le fichier FXML est Login.fxml, remplacez toute la scène
                    contentPane.getScene().setRoot(content);
                } else {
                    // Sinon, chargez le contenu dans le conteneur central
                    contentPane.setCenter(content);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erreur lors du chargement du fichier FXML : " + fxmlFile);
            }
        }
    }

    /**
     * Détermine le fichier FXML associé à un identifiant donné.
     */
    private String determineFXMLFile(String id) {
        switch (id) {
            // View du menu Employé
            case "BenregistrerClientMenu": return "/Fxml/Employe/EnregistrerClient.fxml";
            case "BsupprimerclientMenu": return "/Fxml/Employe/SupprimerClient.fxml";
            case "BlisteClientsMenu": return "/Fxml/Employe/ListeClients.fxml";
            case "BcreerTournoiMenu": return "/Fxml/Employe/CreerTournoi.fxml";
            case "BinscrireJoueureMenu": return "/Fxml/Employe/InscrireJoueure.fxml";
            case "BlisteParticipantsTournoiMenu": return "/Fxml/Employe/ListeParticipants.fxml";
            case "BhistoriqueTournoisMenu": return "/Fxml/Employe/HistoriqueTournoi.fxml";
            case "BsupprimerJeuMenu": return "/Fxml/Employe/SupprimerJeu.fxml";
            case "BajouterJeuMenu": return "/Fxml/Employe/AjouterJeu.fxml";
            case "BlisteJeuxMenu": return "/Fxml/Employe/ListeJeu.fxml";
            case "BmodifierJeuMenu": return "/Fxml/Employe/ModifierJeu.fxml";
            // View du menu Admin
            case "BlisteCompteBloquerMenu": return "/Fxml/Admin/ListeCompteBloquer.fxml";
            case "BdebloquerCompteMenu": return "/Fxml/Admin/DebloquerCompte.fxml";
            case "BsupprimerEmployeMenu": return "/Fxml/Admin/SupprimerEmploye.fxml";
            case "BajouterEmployeMenu": return "/Fxml/Admin/AjouterEmploye.fxml";
            case "BlisteEmployeMenu": return "/Fxml/Admin/ListeEmploye.fxml";
            case "BmodifierEmployeMenu": return "/Fxml/Admin/ModifierEmploye.fxml";
            case "BdeconnecterMenu": return "/Fxml/Login.fxml";
            default:
                System.err.println("Aucun fichier FXML associé à l'ID : " + id);
                return "";
        }
    }
}
