package com.example.playmaster.Controllers.Admin;


import com.example.playmaster.Models.LoginModel;import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

    public class DebloquerCompteController {

        @FXML
        private TextField TidCompteDebloquer; // Champ pour l'identifiant du compte
        @FXML
        private TextField TmotdepasseCompteDebloquer; // Champ pour le mot de passe
        @FXML
        private Label messageLabel; // Label pour afficher les messages

        @FXML
        private void debloquerCompte() throws SQLException {
            String id = TidCompteDebloquer.getText();
            String motdepasse = TmotdepasseCompteDebloquer.getText();

            if (id.isEmpty() || motdepasse.isEmpty()) {
                messageLabel.setText("Veuillez remplir tous les champs.");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Vérification si le mot de passe est correct et si le compte est bloqué
            boolean compteDebloque = LoginModel.debloquerCompte(id, motdepasse);

            if (compteDebloque) {
                messageLabel.setText("Compte débloqué avec succès.");
                messageLabel.setStyle("-fx-text-fill: green;");


            } else {
                messageLabel.setText("Identifiants incorrects ou compte déjà débloqué.");
                messageLabel.setStyle("-fx-text-fill: orange;");
            }
        }
    }


