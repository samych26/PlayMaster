package com.example.playmaster.Controllers;

import com.example.playmaster.Models.LoginModel;
import com.example.playmaster.Views.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;



public class LoginController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField Tid;

    @FXML
    private PasswordField Tmotdepasse;

    @FXML
    private ToggleGroup Buttongroupe1;

    @FXML
    private RadioButton employeRadioButton;

    @FXML
    private RadioButton adminRadioButton;

    @FXML
    private Label erreurLab;

    @FXML
    private Button Bconnecter;


    @FXML
    private void GestionConnexion() throws SQLException {
        String id = Tid.getText();
        String motdepasse = Tmotdepasse.getText();
        RadioButton selectedType = (RadioButton) Buttongroupe1.getSelectedToggle();

        if (id.isEmpty() || motdepasse.isEmpty() || selectedType == null) {
            erreurLab.setText("Veuillez remplir tous les champs.");
            erreurLab.setStyle("-fx-text-fill: red;");
            return;
        }

        String accountType = selectedType.getText(); // Récupère le texte du bouton sélectionné

        if (LoginModel.validationCompte(id, motdepasse, accountType)) {
            erreurLab.setText("");
            naviguerMenu(accountType);
        } else {

            if (LoginModel.isCompteBloque(id, accountType)) {
                erreurLab.setText("Le compte est bloqué. Veuillez contacter l'administrateur.");
                erreurLab.setStyle("-fx-text-fill: red;");
            } else {
                erreurLab.setText("Identifiants incorrects.");
                erreurLab.setStyle("-fx-text-fill: red;");
            }
        }
    }


    @FXML
    private void naviguerMenu(String accountType) {
        try {
            FXMLLoader loader = new FXMLLoader();

            // Charger le fichier FXML en fonction du type d'utilisateur
            if ("Admin".equals(accountType)) {
                loader.setLocation(getClass().getResource("/Fxml/Admin/AdminMenu.fxml"));
            } else if ("Employé".equals(accountType)) {
                loader.setLocation(getClass().getResource("/Fxml/Employe/EmployeMenu.fxml"));
            }

            Parent root = loader.load();

            // Récupérer le contrôleur de la vue (ViewController)
            ViewController controller = loader.getController();

            // Définir le message selon le type de compte
            if ("Admin".equals(accountType)) {
                controller.setWelcomeMessage("Bienvenue, " + LoginModel.identifiantConnecte);
            } else if ("Employé".equals(accountType)) {
                controller.setWelcomeMessage("Bienvenue " + LoginModel.identifiantConnecte);
            }

            // Afficher la nouvelle scène
            Stage stage = (Stage) Bconnecter.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            erreurLab.setText("Erreur de navigation : " + e.getMessage());
        }
    }



}
