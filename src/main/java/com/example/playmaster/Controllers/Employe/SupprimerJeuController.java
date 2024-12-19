package com.example.playmaster.Controllers.Employe;
import com.example.playmaster.Models.SupprimerJeuModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class SupprimerJeuController {
    @FXML
    private TextField TnomJeuSupprimer;

    @FXML
    private TextField TemplacementJeuSupprimer;

    @FXML
    private Button BsupprimerJeu;

    @FXML
    private Label messageLabel;

    @FXML
    private void supprimerJeu() {
        // Récupération des valeurs des champs
        String nomJeu = TnomJeuSupprimer.getText().trim();
        String emplacement = TemplacementJeuSupprimer.getText().trim();

        // Validation des champs
        if (nomJeu.isEmpty() || emplacement.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs !");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Validation du nom du jeu (lettres, chiffres et espaces autorisés)
        if (!nomJeu.matches("[a-zA-Z0-9À-ÿ\\s]+")) {
            messageLabel.setText("Le nom du jeu peut contenir des lettres, des chiffres et des espaces.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        // Validation de l'emplacement du jeu (lettres, chiffres, espaces, tirets et numéros autorisés)
        if (!emplacement.matches("[a-zA-Z0-9À-ÿ\\s\\-]+")) {
            messageLabel.setText("L'emplacement du jeu peut contenir des lettres, des chiffres, des espaces et des tirets.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Appel au modèle pour supprimer le jeu
            boolean success = SupprimerJeuModel.supprimerJeu(nomJeu, emplacement);

            if (success) {
                messageLabel.setText("Jeu supprimé avec succès !");
                messageLabel.setStyle("-fx-text-fill: green;");
                clearFields();
            } else {
                messageLabel.setText("Aucun jeu trouvé avec ces informations.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }

        } catch (Exception e) {
            messageLabel.setText("Erreur lors de la suppression du jeu.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    // Réinitialisation des champs après l'opération
    private void clearFields() {
        TnomJeuSupprimer.clear();
        TemplacementJeuSupprimer.clear();
    }
}
