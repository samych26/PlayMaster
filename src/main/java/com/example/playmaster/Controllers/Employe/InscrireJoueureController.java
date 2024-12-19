package com.example.playmaster.Controllers.Employe;

import com.example.playmaster.Models.EnregistrerParticipantModel;
import com.example.playmaster.Models.Tournoi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class InscrireJoueureController {
    @FXML
    private TextField idTournoi;
    @FXML
    private TextField TnomParticipantInscrit;

    @FXML
    private TextField TprenomParticipantInscrit;

    @FXML
    private TextField TageParticipantInscrit;

    @FXML
    private TextField TdispoJeuAjouter;

    @FXML
    private Button BinscrireParticipant;

    @FXML
    private Label messageLabel;

    @FXML
    public void enregistrerParticipant(ActionEvent event) {
        String id = idTournoi.getText().trim();
        String nom = TnomParticipantInscrit.getText().trim();
        String prenom = TprenomParticipantInscrit.getText().trim();
        String ageText = TageParticipantInscrit.getText().trim();
        String email = TdispoJeuAjouter.getText().trim();

        // Validation des champs
        if (id.isEmpty() || nom.isEmpty() || prenom.isEmpty() || ageText.isEmpty() || email.isEmpty()) {
            messageLabel.setText("Tous les champs doivent être remplis.");
            messageLabel.setStyle("-fx-text-fill: orange;");
            return;
        }

        int idTournoiInt;
        try {
            idTournoiInt = Integer.parseInt(id); // Vérification si l'ID est un entier
        } catch (NumberFormatException e) {
            messageLabel.setText("L'ID du tournoi doit être un nombre valide.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText); // Vérification si l'âge est un entier
        } catch (NumberFormatException e) {
            messageLabel.setText("L'âge doit être un nombre valide.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (!isValidEmail(email)) {
            messageLabel.setText("Veuillez entrer une adresse email valide.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Vérification du nombre de participants
            int nombreParticipantsMax = EnregistrerParticipantModel.getNombreParticipantsMax(idTournoiInt);
            int nombreParticipantsActuels = EnregistrerParticipantModel.getNombreParticipantsActuels();

            if (nombreParticipantsActuels < nombreParticipantsMax) {
                // Inscription du participant
                boolean success = EnregistrerParticipantModel.enregistrerParticipant(nom, prenom, age, email, Integer.parseInt(id));

                if (success) {
                    messageLabel.setText("Participant inscrit avec succès !");
                    messageLabel.setStyle("-fx-text-fill: green;");
                    clearFields();
                } else {
                    messageLabel.setText("Erreur lors de l'inscription. Veuillez réessayer.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            } else {
                messageLabel.setText("Le nombre maximum de participants a été atteint.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur de base de données : " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private boolean isValidEmail(String email) {
        // Expression régulière pour vérifier le format de l'email
        String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private void clearFields() {
        idTournoi.clear();
        TnomParticipantInscrit.clear();
        TprenomParticipantInscrit.clear();
        TageParticipantInscrit.clear();
        TdispoJeuAjouter.clear();
    }
}
