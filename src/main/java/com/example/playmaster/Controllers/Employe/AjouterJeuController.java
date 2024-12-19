package com.example.playmaster.Controllers.Employe;

import com.example.playmaster.Models.AjouterJeuModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class AjouterJeuController {

    // Déclaration des éléments de l'interface FXML
    @FXML
    private TextField TnomJeuAjouter;
    @FXML
    private TextField TtypeJeuAjouter;
    @FXML
    private TextField TemplacementJeuAjouter;
    @FXML
    private TextField TdispoJeuAjouter;
    @FXML
    private TextField TprixSessionJeuAjouter;
    @FXML
    private Button BajouterJeu;
    @FXML
    private Label messageLabel;

    // Méthode pour ajouter un jeu
    @FXML
    private void ajouterJeu() {
        // Récupérer les valeurs des champs de texte
        String nomJeu = TnomJeuAjouter.getText().trim();
        String typeJeu = TtypeJeuAjouter.getText().trim();
        String emplacementJeu = TemplacementJeuAjouter.getText().trim();
        String dispoJeu = TdispoJeuAjouter.getText().trim();
        String prixSessionJeu = TprixSessionJeuAjouter.getText().trim();

        // Validation des champs (s'assurer qu'ils ne sont pas vides)
        if (nomJeu.isEmpty() || typeJeu.isEmpty() || emplacementJeu.isEmpty() ||
                dispoJeu.isEmpty() || prixSessionJeu.isEmpty()) {
            messageLabel.setText("Tous les champs doivent être remplis.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Validation du prix de la session (doit être un nombre positif)
        double prixSession = 0;
        try {
            prixSession = Double.parseDouble(prixSessionJeu);
            if (prixSession <= 0) {
                messageLabel.setText("Le prix de la session doit être un nombre positif.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Le prix de la session doit être un nombre valide.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

// Validation du nom du jeu (lettres, chiffres et espaces autorisés)
        if (!nomJeu.matches("[a-zA-Z0-9À-ÿ\\s]+")) {
            messageLabel.setText("Le nom du jeu peut contenir des lettres, des chiffres et des espaces.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

// Validation du type du jeu (lettres, chiffres et espaces autorisés)
        if (!typeJeu.matches("[a-zA-Z0-9À-ÿ\\s]+")) {
            messageLabel.setText("Le type du jeu peut contenir des lettres, des chiffres et des espaces.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

// Validation de l'emplacement du jeu (lettres, chiffres, espaces, tirets et numéros autorisés)
        if (!emplacementJeu.matches("[a-zA-Z0-9À-ÿ\\s\\-]+")) {
            messageLabel.setText("L'emplacement du jeu peut contenir des lettres, des chiffres, des espaces et des tirets.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }


            boolean ajoutRéussi = AjouterJeuModel.ajouterJeu(nomJeu, typeJeu, emplacementJeu, dispoJeu, Double.parseDouble(prixSessionJeu));

            if (ajoutRéussi) {
                messageLabel.setText("Jeu ajouté avec succès !");
                messageLabel.setStyle("-fx-text-fill: green;");
                clearFields();
            } else {
                messageLabel.setText("Erreur lors de l'ajout du jeu.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }

    }

    // Méthode pour vider les champs de texte
    private void clearFields() {
        TnomJeuAjouter.clear();
        TtypeJeuAjouter.clear();
        TemplacementJeuAjouter.clear();
        TdispoJeuAjouter.clear();
        TprixSessionJeuAjouter.clear();
    }
}

