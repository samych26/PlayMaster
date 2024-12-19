package com.example.playmaster.Controllers.Employe;

import com.example.playmaster.Models.HistoriqueTournoisModel;
import com.example.playmaster.Models.Participant;
import com.example.playmaster.Models.Tournoi;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class HistoriqueTournoisController {
    @FXML
    private TextField TsearchTournoi;
    @FXML
    private Button BafficherHistoriqueTournoi;

    @FXML
    private Label messageLabel;

    @FXML
    private TableView<Tournoi> tableViewTournoi;

    @FXML
    private TableColumn<Tournoi, String> nomTournoi;

    @FXML
    private TableColumn<Tournoi, String> typeTournoi;

    @FXML
    private TableColumn<Tournoi, LocalDate> dateDebut;

    @FXML
    private TableColumn<Tournoi, LocalDate> dateFin;

    @FXML
    private TableColumn<Tournoi, Integer> nombreParticipants;

    @FXML
    private TableColumn<Tournoi, Double> fraisInscription;

    @FXML
    private TableColumn<Tournoi, String> recompense;

    // partie pour les participants
    @FXML
    private TableView<Participant> tableViewParticipants;

    @FXML
    private TableColumn<Participant, String> colNomParticipant;

    @FXML
    private TableColumn<Participant, String> colPrenomParticipant;

    @FXML
    private TableColumn<Participant, Integer> colAgeParticipant;

    @FXML
    private TableColumn<Participant, String> colEmailParticipant;


    public void initialize() {
        // Initialiser les colonnes du TableView
        nomTournoi.setCellValueFactory(new PropertyValueFactory<>("nomTournoi"));
        typeTournoi.setCellValueFactory(new PropertyValueFactory<>("typeTournoi"));
        dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        nombreParticipants.setCellValueFactory(new PropertyValueFactory<>("nombreParticipants"));
        fraisInscription.setCellValueFactory(new PropertyValueFactory<>("fraisInscription"));
        recompense.setCellValueFactory(new PropertyValueFactory<>("recompense"));

        // partie pour les participants
        colNomParticipant.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenomParticipant.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colAgeParticipant.setCellValueFactory(new PropertyValueFactory<>("age"));
        colEmailParticipant.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableViewTournoi.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleTournoiSelection();
        });

        // Ajouter un écouteur pour détecter les changements dans TsearchTournoi
        TsearchTournoi.textProperty().addListener((observable, oldValue, newValue) -> {
            searchTournois(newValue);
        });

        tableViewTournoi.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleTournoiSelection();
        });



    }
    @FXML
    private void getListeTournoi() {
        try {
            // Récupérer la liste des tournois depuis le modèle
            List<Tournoi> tournois = HistoriqueTournoisModel.getListeTournois();

            if (tournois.isEmpty()) {
                // Si aucun tournoi n'est trouvé
                messageLabel.setText("Aucun tournoi trouvé.");
                messageLabel.setStyle("-fx-text-fill: orange;"); // Couleur orange pour avertissement
            } else {
                // Remplir la TableView avec les données récupérées
                tableViewTournoi.getItems().clear();
                tableViewTournoi.getItems().addAll(tournois);

                // Afficher un message de réussite
                messageLabel.setText("Liste des tournois chargée avec succès.");
                messageLabel.setStyle("-fx-text-fill: green;"); // Couleur verte pour succès
            }
        } catch (Exception e) {
            // En cas d'erreur, afficher un message d'erreur
            messageLabel.setText("Erreur lors du chargement des tournois : " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;"); // Couleur rouge pour erreur
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTournoiSelection() {
        // Récupérer le tournoi sélectionné
        Tournoi selectedTournoi = tableViewTournoi.getSelectionModel().getSelectedItem();

        if (selectedTournoi != null) {
            int tournoiId = selectedTournoi.getIdTournoi();
            try {
                // Récupérer les participants du tournoi sélectionné
                List<Participant> participants = HistoriqueTournoisModel.getParticipantsByTournoi(tournoiId);



                if (participants.isEmpty()) {
                    messageLabel.setText("Aucun participant trouvé pour ce tournoi.");
                    messageLabel.setStyle("-fx-text-fill: orange;");
                } else {
                    tableViewParticipants.getItems().clear();
                    tableViewParticipants.getItems().addAll(participants);
                    messageLabel.setText("Participants chargés avec succès.");
                    messageLabel.setStyle("-fx-text-fill: green;");
                }
            } catch (Exception e) {


                messageLabel.setText("Erreur lors du chargement des participants : " + e.getMessage());
                messageLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Veuillez sélectionner un tournoi.");
            messageLabel.setStyle("-fx-text-fill: orange;");
        }
    }

    private void searchTournois(String searchText) {
        try {
            // Si le champ de recherche est vide, charger tous les tournois
            if (searchText == null || searchText.trim().isEmpty()) {
                getListeTournoi(); // Méthode existante pour charger tous les tournois
                return;
            }

            // Sinon, récupérer les tournois correspondant au texte saisi
            List<Tournoi> filteredTournois = HistoriqueTournoisModel.searchTournois(searchText.trim());

            if (filteredTournois.isEmpty()) {
                messageLabel.setText("Aucun tournoi correspondant trouvé.");
                messageLabel.setStyle("-fx-text-fill: orange;");
                tableViewTournoi.getItems().clear();
            } else {
                tableViewTournoi.getItems().setAll(filteredTournois);
                messageLabel.setText("Résultats affichés.");
                messageLabel.setStyle("-fx-text-fill: green;");
            }
        } catch (Exception e) {
            messageLabel.setText("Erreur lors de la recherche : " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }


}
