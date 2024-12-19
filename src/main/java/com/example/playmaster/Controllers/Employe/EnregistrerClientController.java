package com.example.playmaster.Controllers.Employe;

import com.example.playmaster.Models.EnregistrerClientModel;
import com.example.playmaster.Models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.time.LocalDate;

public class EnregistrerClientController {
    @FXML
    private TextField TnomClientEnregistrer;

    @FXML
    private TextField TprenomClientEnregistrer;

    @FXML
    private DatePicker TdateNaissanceClientEnregistrer;

    @FXML
    private TextField TprixSessionJeuAjouter;

    @FXML
    private Button BenregistrerClient;

    @FXML
    private Label messageLabel;

    @FXML
    private void enregistrerClient(ActionEvent event) {
        // Récupérer les valeurs saisies
        String nom = TnomClientEnregistrer.getText().trim();
        String prenom = TprenomClientEnregistrer.getText().trim();
        LocalDate dateNaissance = TdateNaissanceClientEnregistrer.getValue();
        String telephone = TprixSessionJeuAjouter.getText().trim();
        // Création de l'objet Client
        Client client = new Client(nom, prenom, dateNaissance, telephone);

        // Validation des champs
        if (nom.isEmpty() || prenom.isEmpty() || dateNaissance==null || telephone.isEmpty()) {
            messageLabel.setText("Tous les champs doivent être remplis.");
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

        // Validation du numéro de téléphone : doit commencer par 05, 06 ou 07 et avoir exactement 10 chiffres
        if (!telephone.matches("^(05|06|07)\\d{8}$")) {
            messageLabel.setText("Le numéro de téléphone doit commencer par 05, 06 ou 07 et contenir exactement 10 chiffres.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Ajout dans la base de données ou tout autre logique
        boolean ajoutReussi = EnregistrerClientModel.enregistrerClient(client);

        if (ajoutReussi) {
            messageLabel.setText("Client enregistré avec succès !");
            messageLabel.setStyle("-fx-text-fill: green;");
            clearFields();
        } else {
            messageLabel.setText("Erreur lors de l'enregistrement du client.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void clearFields() {
        TnomClientEnregistrer.clear();
        TprenomClientEnregistrer.clear();
        TdateNaissanceClientEnregistrer.setValue(null);
        TprixSessionJeuAjouter.clear();
    }
}




