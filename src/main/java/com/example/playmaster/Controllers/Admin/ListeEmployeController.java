package com.example.playmaster.Controllers.Admin;

import com.example.playmaster.Models.Employe;
import com.example.playmaster.Models.ListeEmployeModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ListeEmployeController {

    @FXML
    private TextField TsearchEmploye;

    @FXML
    private TableView<Employe> tableViewEmploye;

    @FXML
    private TableColumn<Employe, String> colNomEmploye;

    @FXML
    private TableColumn<Employe, String> colPrenomEmploye;

    @FXML
    private TableColumn<Employe, String> colDateNaissance;

    @FXML
    private TableColumn<Employe, String> colTelephone;

    @FXML
    private TableColumn<Employe, String> colPoste;

    @FXML
    private TableColumn<Employe, Double> colSalaire;

    @FXML
    private TableColumn<Employe, String> colDateEmbauche;

    @FXML
    private TableColumn<Employe, String> colStatut;

    @FXML
    private Label messageLabel;

    @FXML
    private void initialize() {
        // Configuration des colonnes
        colNomEmploye.setCellValueFactory(new PropertyValueFactory<>("nomEmploye"));
        colPrenomEmploye.setCellValueFactory(new PropertyValueFactory<>("prenomEmploye"));
        colDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateDeNaissance"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colPoste.setCellValueFactory(new PropertyValueFactory<>("poste"));
        colSalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        colDateEmbauche.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
        colStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));



        TsearchEmploye.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.trim().isEmpty()) {
                // Rechercher seulement si la saisie contient au moins 1 caractère
                rechercherEmploye(newValue.trim());
            } else {
                // Si la saisie est vide, vous pouvez décider de vider la table ou de réafficher la liste complète
                tableViewEmploye.getItems().clear();
            }
        });
    }

    @FXML
    private void afficherListeEmployes() {
        try {
            List<Employe> employes = ListeEmployeModel.getListeEmploye();

            if (employes.isEmpty()) {
                messageLabel.setText("Aucun employé trouvé.");
                messageLabel.setStyle("-fx-text-fill: orange;");
                tableViewEmploye.getItems().clear();
            } else {
                tableViewEmploye.getItems().setAll(employes);
                messageLabel.setText("Liste des employés chargée avec succès.");
                messageLabel.setStyle("-fx-text-fill: green;");
            }
        } catch (Exception e) {
            messageLabel.setText("Erreur lors du chargement des employés : " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void rechercherEmploye(String searchText) {
        try {
            List<Employe> employes = ListeEmployeModel.rechercherEmploye(searchText);

            if (employes.isEmpty()) {
                messageLabel.setText("Aucun employé correspondant à la recherche.");
                messageLabel.setStyle("-fx-text-fill: orange;");
                tableViewEmploye.getItems().clear();
            } else {
                tableViewEmploye.getItems().setAll(employes);
                messageLabel.setText("Résultats de recherche affichés.");
                messageLabel.setStyle("-fx-text-fill: green;");
            }
        } catch (Exception e) {
            messageLabel.setText("Erreur lors de la recherche : " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
}
