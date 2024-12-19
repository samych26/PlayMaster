package com.example.playmaster.Controllers.Admin;

import com.example.playmaster.Models.SupprimerClientModel;
import com.example.playmaster.Models.SupprimerEmployeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class SupprimerEmployeController {
    @FXML
    private TextField TnomEmployeSupprimer;

    @FXML
    private TextField TprenomEmployeSupprimer;

    @FXML
    private DatePicker TdateNaissanceEmployeSupprimer;

    @FXML
    private Button BsupprimerEmploye;

    @FXML
    private Label messageLabel;
    @FXML
    private void supprimerEmploye() {
        // Récupération des valeurs entrées par l'utilisateur
        String nom = TnomEmployeSupprimer.getText().trim();
        String prenom = TprenomEmployeSupprimer.getText().trim();
        LocalDate dateNaissance = TdateNaissanceEmployeSupprimer.getValue();

        if (nom.isEmpty() || prenom.isEmpty() || dateNaissance == null) {
            messageLabel.setText("Veuillez remplir tous les champs !");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Validation du nom (doit contenir uniquement des lettres et des espaces)
        if (!nom.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            messageLabel.setText("Le nom ne peut contenir que des lettres et des espaces !");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

// Validation du prénom (doit contenir uniquement des lettres et des espaces)
        if (!prenom.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            messageLabel.setText("Le prénom ne peut contenir que des lettres et des espaces !");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

// Vérification de la date de naissance (elle ne doit pas être dans le futur)
        if (dateNaissance.isAfter(LocalDate.now())) {
            messageLabel.setText("La date de naissance ne peut pas être dans le futur !");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Appel au modèle pour supprimer le client
            boolean success = SupprimerEmployeModel.supprimerEmploye(nom,prenom,dateNaissance);

            if (success) {
                messageLabel.setText("Employé supprimé avec succès !");
                messageLabel.setStyle("-fx-text-fill: green;");
                clearFields();
            } else {
                messageLabel.setText("Aucun Employé trouvé avec ces informations.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }

        } catch (Exception e) {
            messageLabel.setText("Erreur lors de la suppression de Employé.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }

    }
    private void clearFields() {
        TnomEmployeSupprimer.clear();
        TprenomEmployeSupprimer.clear();
        TdateNaissanceEmployeSupprimer.setValue(null);
    }
}


