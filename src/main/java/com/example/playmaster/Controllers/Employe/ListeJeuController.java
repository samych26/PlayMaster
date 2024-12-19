package com.example.playmaster.Controllers.Employe;

import com.example.playmaster.Models.Jeu;
import com.example.playmaster.Models.ListeJeuModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;


public class ListeJeuController {

    @FXML
    private TextField TsearchJeu;
    @FXML
    private TableView<Jeu> tableViewJeux;

    @FXML
    private TableColumn<Jeu, String> colNom;

    @FXML
    private TableColumn<Jeu, String> colTypeJeu;

    @FXML
    private TableColumn<Jeu, String> colEmplacement;

    @FXML
    private TableColumn<Jeu, String> colDisponibilite;

    @FXML
    private TableColumn<Jeu, String> colPrixSession;

    @FXML
    private Label messageLabel;

    private ObservableList<Jeu> jeuxList = FXCollections.observableArrayList();
    @FXML
    private void initialize() {
        // Configurer les colonnes de la TableView
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colTypeJeu.setCellValueFactory(new PropertyValueFactory<>("typeJeu"));
        colEmplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        colDisponibilite.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        colPrixSession.setCellValueFactory(new PropertyValueFactory<>("prixParSession"));
        // Mettre à jour la TableView à chaque modification dans le champ de recherche
        TsearchJeu.textProperty().addListener((observable, oldValue, newValue) -> {
            // Appel de la méthode pour rechercher les jeux
            ObservableList<Jeu> filteredJeux = ListeJeuModel.rechercherJeu(newValue);
            // Mettre à jour la TableView avec les résultats de la recherche
            tableViewJeux.setItems(filteredJeux);
        });

        // Initialiser la TableView avec la liste observable vide
        tableViewJeux.setItems(jeuxList);
    }


    @FXML
    public void afficherListeJeux() {
        try {
            // Effacer les données actuelles
            jeuxList.clear();

            // Récupérer les jeux depuis le modèle
            List<Jeu> jeux = ListeJeuModel.getListeJeu();  // Cette méthode lance maintenant une SQLException
            jeuxList.addAll(jeux);

            // Mettre à jour la TableView avec les jeux
            tableViewJeux.setItems(jeuxList);

            // Mise à jour du message
            messageLabel.setText("Liste des jeux mise à jour avec succès.");
            messageLabel.setStyle("-fx-text-fill: green;");  // Couleur verte pour le succès

        } catch (SQLException e) {
            // Afficher l'exception en cas d'erreur SQL
            e.printStackTrace();
            messageLabel.setText("Erreur lors du chargement des jeux.");
            messageLabel.setStyle("-fx-text-fill: red;");

            // Réinitialiser la TableView
            tableViewJeux.setItems(FXCollections.observableArrayList());
        }
    }

}
