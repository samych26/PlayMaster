package com.example.playmaster.Controllers.Employe;
import com.example.playmaster.Models.ModifierJeuModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class ModifierJeuController {
    @FXML
    private TextField TnomJeumodifier;

    @FXML
    private TextField TtypeJeuModifer;

    @FXML
    private TextField TemplacementJeuModifier;

    @FXML
    private TextField TdispoJeuModifier;

    @FXML
    private TextField TprixSessionJeuModifier;

    @FXML
    private Button BmodifierJeu;

    @FXML
    private Label messageLabel;

    @FXML
    public void modifierJeu(ActionEvent event) {
        // Récupérer les valeurs saisies par l'utilisateur
        String nomJeu = TnomJeumodifier.getText().trim();
        String typeJeu = TtypeJeuModifer.getText().trim();
        String emplacement = TemplacementJeuModifier.getText().trim();
        String disponibilite = TdispoJeuModifier.getText().trim();
        String prixSessionStr = TprixSessionJeuModifier.getText().trim();


        if (nomJeu.isEmpty()) {
            messageLabel.setText("Vous devez remplire le champ Nom  !");
            messageLabel.setStyle("-fx-text-fill: orange;");
            return;
        }
        if ( typeJeu.isEmpty() && emplacement.isEmpty() && disponibilite.isEmpty() && prixSessionStr.isEmpty()) {
            messageLabel.setText("Vous devez entrer au moin une modification !");
            messageLabel.setStyle("-fx-text-fill: orange;");
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


        try {
            double prixSession = Double.parseDouble(prixSessionStr); // Conversion du prix en double


            if (prixSession < 0 ) {
                messageLabel.setText("Le prix par session doit être positif !");
                messageLabel.setStyle("-fx-text-fill: orange;");
                return;
            }

            // Appeler une méthode pour mettre à jour le jeu dans la base de données
            boolean success = ModifierJeuModel.modifierJeu(nomJeu, typeJeu, emplacement, disponibilite, prixSession);

            // Afficher un message selon le résultat
            if (success) {
                messageLabel.setText("Le jeu a été modifié avec succès !");
                messageLabel.setStyle("-fx-text-fill: green;");
            } else {
                messageLabel.setText("Erreur lors de la modification du jeu.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Le prix par session doit être un nombre valide !");
            messageLabel.setStyle("-fx-text-fill: orange;"); 
        }


    }

}
