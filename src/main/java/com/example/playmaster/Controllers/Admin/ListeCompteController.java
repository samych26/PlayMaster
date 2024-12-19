package com.example.playmaster.Controllers.Admin;

import com.example.playmaster.Models.CompteBloque;
import com.example.playmaster.Models.ComptesBloquesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.List;

public class ListeCompteController {
    @FXML
    private TextField TsearchCompte;  // Champ de recherche
    @FXML
    private TableView<CompteBloque> tableViewEmploye;  // TableView des comptes bloqués
    @FXML
    private TableColumn<CompteBloque, String> Identifiant;
    @FXML
    private TableColumn<CompteBloque, String> Mot_de_passe;
    @FXML
    private TableColumn<CompteBloque, String> Date_bloquage;
    @FXML
    private TableColumn<CompteBloque, String> raison_bloquage;
    @FXML
    private Label messageLabel;

    private ObservableList<CompteBloque> comptesBloquesList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialiser les colonnes de la TableView
        Identifiant.setCellValueFactory(new PropertyValueFactory<>("identifiant"));
        Mot_de_passe.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));
        Date_bloquage.setCellValueFactory(new PropertyValueFactory<>("dateBlocage"));
        raison_bloquage.setCellValueFactory(new PropertyValueFactory<>("raisonBlocage"));

        // Mettre à jour la TableView à chaque modification dans le champ de recherche
        TsearchCompte.textProperty().addListener((observable, oldValue, newValue) -> {
            // Appel de la méthode pour rechercher les comptes bloqués
            ObservableList<CompteBloque> filteredComptes = ComptesBloquesModel.rechercherCompteBloque(newValue);
            // Mettre à jour la TableView avec les résultats de la recherche
            tableViewEmploye.setItems(filteredComptes);
        });

        // Initialiser la TableView avec la liste observable vide
        tableViewEmploye.setItems(comptesBloquesList);
    }

    @FXML
    public void chargerComptesBloques() {
        try {
            // Effacer les données actuelles
            comptesBloquesList.clear();

            // Récupérer les comptes bloqués depuis le modèle
            List<CompteBloque> comptes = ComptesBloquesModel.getComptesBloques();
            comptesBloquesList.addAll(comptes);

            // Mettre à jour la TableView avec les comptes bloqués
            tableViewEmploye.setItems(comptesBloquesList);

            // Mise à jour du message
            messageLabel.setText("Liste des comptes bloqués mise à jour avec succès.");
            messageLabel.setStyle("-fx-text-fill: green;"); // Couleur verte pour succès

        } catch (SQLException e) {
            e.printStackTrace();
            messageLabel.setText("Erreur lors du chargement des comptes bloqués.");
            messageLabel.setStyle("-fx-text-fill: red;"); // Couleur rouge pour erreur
        }
    }
}
