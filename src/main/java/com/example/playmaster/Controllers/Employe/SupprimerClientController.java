package com.example.playmaster.Controllers.Employe;

import com.example.playmaster.Models.Client;
import com.example.playmaster.Models.SupprimerClientModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class SupprimerClientController {
    @FXML
    private TextField TnomClientSupprimer;

    @FXML
    private TextField TprenomClientSupprimer;

    @FXML
    private DatePicker TdateNaissanceClientSupprimer;

    @FXML
    private TextField TtelephoneClientSupprimer;

    @FXML
    private Button BsupprimerClient;

    @FXML
    private Label messageLabel;


    @FXML
    private void supprimerClient() {
        // Récupération des données utilisateur
        String nom = TnomClientSupprimer.getText();
        String prenom = TprenomClientSupprimer.getText();
        LocalDate dateNaissance = TdateNaissanceClientSupprimer.getValue();
        String telephone = TtelephoneClientSupprimer.getText();

        // Validation des champs
        if (!validerChamps(nom, prenom, dateNaissance, telephone)) {
            messageLabel.setText("Veuillez remplir tous les champs correctement.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!nom.matches("[a-zA-ZÀ-ÿ\\s]+") || !prenom.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            messageLabel.setText("Le nom et le prénom ne peuvent contenir que des lettres et des espaces.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (dateNaissance.isAfter(LocalDate.now())) {
            messageLabel.setText("La date de naissance ne peut pas être dans le futur.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Création d'un objet client
        Client client = new Client(nom, prenom, dateNaissance, telephone);

        try {
            // Appel au modèle pour supprimer le client
            boolean success = SupprimerClientModel.supprimerClient(client);

            if (success) {
                messageLabel.setText("Client supprimé avec succès !");
                messageLabel.setStyle("-fx-text-fill: green;");
                clearFields();
            } else {
                messageLabel.setText("Aucun client trouvé avec ces informations.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }

        } catch (Exception e) {
            messageLabel.setText("Erreur lors de la suppression du client.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    // Méthode pour valider les champs utilisateur
    private boolean validerChamps(String nom, String prenom, LocalDate dateNaissance, String telephone) {
        if (nom.isEmpty() || prenom.isEmpty() || dateNaissance == null || telephone.isEmpty()) {
            return false;
        }
        if (!telephone.matches("\\d{10}")) { // Vérifie que le numéro contient exactement 10 chiffres
            messageLabel.setText("Le numéro de téléphone doit contenir 10 chiffres.");
            return false;
        }
        return true;
    }

    // Méthode pour réinitialiser les champs
    private void clearFields() {
        TnomClientSupprimer.clear();
        TprenomClientSupprimer.clear();
        TdateNaissanceClientSupprimer.setValue(null);
        TtelephoneClientSupprimer.clear();
    }
    }





