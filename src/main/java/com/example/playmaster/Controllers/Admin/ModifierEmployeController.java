package com.example.playmaster.Controllers.Admin;
import com.example.playmaster.Models.ModifierEmployeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ModifierEmployeController {
    @FXML
    private TextField TnomEmployeModifier;

    @FXML
    private TextField TprenomEmployeModifier;

    @FXML
    private DatePicker TdateNaissanceEmployeModifier;

    @FXML
    private TextField TnumeroTellEmployeModifier;

    @FXML
    private TextField TposteOccupeModifier;

    @FXML
    private TextField TsalaireEmployeModifier;

    @FXML
    private TextField TstautEmployeModifier;

    @FXML
    private Button BmodifierEmploye;

    @FXML
    private Label messageLabel;

    @FXML
    private void modifierEmploye() {
        // Récupération des données des champs
        String nom = TnomEmployeModifier.getText().trim();
        String prenom = TprenomEmployeModifier.getText().trim();
        LocalDate dateNaissance = TdateNaissanceEmployeModifier.getValue();
        String numeroTelephone = TnumeroTellEmployeModifier.getText().trim();
        String poste = TposteOccupeModifier.getText().trim();
        String salaire = TsalaireEmployeModifier.getText().trim();
        String statut = TstautEmployeModifier.getText().trim();



        try {
            // Validation des champs obligatoires : nom, prénom et date de naissance
            if (nom.isEmpty() || prenom.isEmpty() || dateNaissance == null) {
                messageLabel.setText("Vous devez remplir les champs Nom, Prénom et Date de naissance !");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
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
            // Validation de la date de naissance (ne doit pas être dans le futur)
            if (dateNaissance.isAfter(LocalDate.now())) {
                messageLabel.setText("La date de naissance ne peut pas être dans le futur.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Vérification que le téléphone est valide s'il est renseigné
            if (!numeroTelephone.isEmpty() && !numeroTelephone.matches("^(05|06|07)\\d{8}$")) {
                messageLabel.setText("Le numéro de téléphone doit commencer par 05, 06 ou 07 et comporter exactement 10 chiffres.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Vérification qu'au moins une modification a été effectuée (téléphone, poste, salaire, statut)
            if (numeroTelephone.isEmpty() && poste.isEmpty() && salaire.isEmpty() && statut.isEmpty()) {
                messageLabel.setText("Vous devez entrer au moins une modification !");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Validation du salaire (doit être un nombre valide et positif)
            double salaireDouble = 0;
            if (!salaire.isEmpty()) {
                try {
                    salaireDouble = Double.parseDouble(salaire);
                    if (salaireDouble < 0) {
                        messageLabel.setText("Le salaire doit être positif !");
                        messageLabel.setStyle("-fx-text-fill: red;");
                        return;
                    }
                } catch (NumberFormatException e) {
                    messageLabel.setText("Le salaire doit être un nombre valide !");
                    messageLabel.setStyle("-fx-text-fill: red;");
                    return;
                }
            }


            // Appel au modèle pour modifier l'employé
            boolean success = ModifierEmployeModel.modifierEmploye(nom, prenom, dateNaissance, numeroTelephone, poste, salaireDouble, statut);

            if (success) {
                messageLabel.setText("Employé modifié avec succès !");
                messageLabel.setStyle("-fx-text-fill: green;");
                clearFields();
            } else {
                messageLabel.setText("Aucun employé trouvé avec ces informations.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }

        } catch (NumberFormatException e) {
            messageLabel.setText("Une erreur est survenue lors de la validation des données !");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    // Réinitialisation des champs après modification
    private void clearFields() {
        TnomEmployeModifier.clear();
        TprenomEmployeModifier.clear();
        TdateNaissanceEmployeModifier.setValue(null);
        TnumeroTellEmployeModifier.clear();
        TposteOccupeModifier.clear();
        TsalaireEmployeModifier.clear();
        TstautEmployeModifier.clear();
    }
}

