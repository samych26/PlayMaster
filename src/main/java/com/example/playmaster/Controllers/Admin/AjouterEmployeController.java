package com.example.playmaster.Controllers.Admin;

import com.example.playmaster.Models.Employe;
import com.example.playmaster.Models.ajouterEmployeModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class AjouterEmployeController {

    @FXML private TextField TnomEmployeAjouter;
    @FXML private TextField TprenomEmployeAjouter;
    @FXML private DatePicker TdateNaissanceEmployeAjouter;
    @FXML private TextField TnumeroTellEmployeAjouter;
    @FXML private TextField TposteOccupeAjouter;
    @FXML private TextField TsalaireEmployeAjouter;
    @FXML private DatePicker DateEmbaucheEmployeAjouter;
    @FXML private TextField TstautEmployeAjouter;
    @FXML private Label messageLabel;

    @FXML
    private void ajouterEmploye() {
        try {
            // Validation des champs vides
            if (TnomEmployeAjouter.getText().isEmpty() ||
                    TprenomEmployeAjouter.getText().isEmpty() ||
                    TdateNaissanceEmployeAjouter.getValue() == null ||
                    TnumeroTellEmployeAjouter.getText().isEmpty() ||
                    TposteOccupeAjouter.getText().isEmpty() ||
                    TsalaireEmployeAjouter.getText().isEmpty() ||
                    DateEmbaucheEmployeAjouter.getValue() == null ||
                    TstautEmployeAjouter.getText().isEmpty()) {

                messageLabel.setText("Veuillez remplir tous les champs !");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Récupération des valeurs des champs
            String nom = TnomEmployeAjouter.getText();
            String prenom = TprenomEmployeAjouter.getText();
            LocalDate dateNaissance = TdateNaissanceEmployeAjouter.getValue();
            String telephone = TnumeroTellEmployeAjouter.getText();
            String poste = TposteOccupeAjouter.getText();
            double salaire = Double.parseDouble(TsalaireEmployeAjouter.getText());
            LocalDate dateEmbauche = DateEmbaucheEmployeAjouter.getValue();
            String statut = TstautEmployeAjouter.getText();


// Validation des données spécifiques

            // Validation des champs nom et prénom (uniquement lettres et espaces)
            if (!nom.matches("[a-zA-ZÀ-ÿ\\s]+")) {
                messageLabel.setText("Le nom ne peut contenir que des lettres et des espaces.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            if (!prenom.matches("[a-zA-ZÀ-ÿ\\s]+")) {
                messageLabel.setText("Le prénom ne peut contenir que des lettres et des espaces.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Validation du téléphone (doit être composé de 10 chiffres)
            if (!telephone.matches("^(05|06|07)\\d{8}$")) {
                messageLabel.setText("Le numéro de téléphone doit étre valide .");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Validation du salaire (doit être un nombre valide et positif)

            try {

                if (salaire <= 0) {
                    messageLabel.setText("Le salaire doit être un nombre positif.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                    return;
                }
            } catch (NumberFormatException e) {
                messageLabel.setText("Le salaire doit être un nombre valide.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Validation de la date de naissance (ne doit pas être dans le futur)
            if (dateNaissance.isAfter(LocalDate.now())) {
                messageLabel.setText("La date de naissance ne peut pas être dans le futur.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Validation de la date d'embauche (ne doit pas être dans le futur)
            if (dateEmbauche.isAfter(LocalDate.now())) {
                messageLabel.setText("La date d'embauche ne peut pas être dans le futur.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }


            // Création de l'objet Employe
            Employe employe = new Employe(nom, prenom, dateNaissance, telephone, poste, salaire, dateEmbauche, statut);

            // Sauvegarde dans la base de données
            if (ajouterEmployeModel.ajouterEmploye(employe)) {
                messageLabel.setText("Employé ajouté avec succès !");
                messageLabel.setStyle("-fx-text-fill: green;");
                // Nettoyage des champs après succès
                nettoyerChamps();
            } else {
                messageLabel.setText("Erreur lors de l'ajout de l'employé.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Veuillez saisir un salaire valide.");
        } catch (Exception e) {
            messageLabel.setText("Une erreur inattendue est survenue.");
            e.printStackTrace();
        }
    }

    // Méthode pour nettoyer les champs après l'ajout
    private void nettoyerChamps() {
        TnomEmployeAjouter.clear();
        TprenomEmployeAjouter.clear();
        TdateNaissanceEmployeAjouter.setValue(null);
        TnumeroTellEmployeAjouter.clear();
        TposteOccupeAjouter.clear();
        TsalaireEmployeAjouter.clear();
        DateEmbaucheEmployeAjouter.setValue(null);
        TstautEmployeAjouter.clear();
    }
}