package com.example.playmaster.Controllers.Employe;

import com.example.playmaster.Models.CreerTournoiModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.time.LocalDate;

public class CreerTournoiController {
    @FXML
    private TextField TnomTournoiCreer;

    @FXML
    private TextField TtypeTournoiCreer;

    @FXML
    private DatePicker TdateDebutTournoiCreer;

    @FXML
    private DatePicker TdateFinTournoiCreer;

    @FXML
    private TextField TnombreParticipantsTournoiCreer;

    @FXML
    private TextField TfraisInscriptionTournoiCreer;

    @FXML
    private TextField TrecompenseTournoiCreer;

    @FXML
    private Button BcreerTournoi;

    @FXML
    private Label messageLabel;

    @FXML
    private void creerTournoi(ActionEvent event) {
        // Récupérer les valeurs saisies dans les champs de texte
        String nomTournoi = TnomTournoiCreer.getText().trim();
        String typeTournoi = TtypeTournoiCreer.getText().trim();
        LocalDate dateDebut = TdateDebutTournoiCreer.getValue();
        LocalDate dateFin = TdateFinTournoiCreer.getValue();
        String nombreParticipants = TnombreParticipantsTournoiCreer.getText().trim();
        String fraisInscription = TfraisInscriptionTournoiCreer.getText().trim();
        String recompense = TrecompenseTournoiCreer.getText().trim();

        // Validation des dates
        if (dateDebut.isAfter(dateFin)) {
            messageLabel.setText("La date de début ne peut pas être après la date de fin.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Vérification que la date de début n'est pas dans le passé
        if (dateDebut.isBefore(LocalDate.now())) {
            messageLabel.setText("La date de début ne peut pas être dans le passé.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Validation des types des champs numériques
            int nbParticipants = Integer.parseInt(nombreParticipants);
            double frais = Double.parseDouble(fraisInscription);

            // Vérification que le nombre de participants et les frais d'inscription sont positifs
            if (nbParticipants <= 0) {
                messageLabel.setText("Le nombre de participants doit être positif.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            if (frais <= 0) {
                messageLabel.setText("Les frais d'inscription doivent être positifs.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }


            boolean ajoutRéussi = CreerTournoiModel.creerTournoi(nomTournoi, typeTournoi, dateDebut, dateFin, nbParticipants, frais, recompense);

            if (ajoutRéussi) {
                messageLabel.setText("Jeu ajouté avec succès !");
                messageLabel.setStyle("-fx-text-fill: green;");
                clearFields();  //vider les champs après creation
            } else {
                messageLabel.setText("Erreur lors de l'ajout du jeu.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }



        } catch (NumberFormatException e) {
            messageLabel.setText("Veuillez entrer des valeurs valides pour les champs numériques.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
    private void clearFields() {
        TnomTournoiCreer.clear();
        TtypeTournoiCreer.clear();
        TdateDebutTournoiCreer.setValue(null);
        TdateFinTournoiCreer.setValue(null);
        TnombreParticipantsTournoiCreer.clear();
        TfraisInscriptionTournoiCreer.clear();
        TrecompenseTournoiCreer.clear();
    }


}
