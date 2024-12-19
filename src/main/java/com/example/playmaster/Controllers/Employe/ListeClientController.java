package com.example.playmaster.Controllers.Employe;

import com.example.playmaster.Models.Client;
import com.example.playmaster.Models.ListeClientModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ListeClientController {

    @FXML
    private TableView<Client> tableViewClients;

    @FXML
    private TableColumn<Client, String> colNom;

    @FXML
    private TableColumn<Client, String> colPrenom;

    @FXML
    private TableColumn<Client, String> colDateNaiss;

    @FXML
    private TableColumn<Client, String> colTelephone;

    @FXML
    private Label messageLabel;

    @FXML
    private TextField TsearchClient; // Champ de recherche pour les clients

    private List<Client> allClients; // Liste complète des clients pour la recherche

    @FXML
    private void initialize() {
        // Configurer les colonnes
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenomClient"));
        colDateNaiss.setCellValueFactory(new PropertyValueFactory<>("dateDeNaissanceClient"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephoneClient"));

        // Initialiser la liste des clients
        allClients = ListeClientModel.getListeClients();

        TsearchClient.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.trim().isEmpty()) {
                // Filtrer les clients uniquement si l'utilisateur a saisi quelque chose
                filterClients(newValue.trim());
            } else {
                // Si le champ de recherche est vide, vider la table ou réinitialiser la liste
                tableViewClients.getItems().clear();
                messageLabel.setText("Veuillez saisir au moins un caractère pour effectuer une recherche.");
                messageLabel.setStyle("-fx-text-fill: orange;");
            }
        });

    }

    @FXML
    private void afficherListeClients() {
        try {
            // Récupérer la liste des clients depuis le modèle
            List<Client> clients = ListeClientModel.getListeClients();

            if (clients.isEmpty()) {
                // Si aucun client n'est trouvé
                messageLabel.setText("Aucun client trouvé.");
                messageLabel.setStyle("-fx-text-fill: orange;"); // Couleur orange pour avertissement
            } else {
                // Remplir la TableView avec les données récupérées
                tableViewClients.getItems().clear();
                tableViewClients.getItems().addAll(clients);

                // Afficher un message de réussite
                messageLabel.setText("Liste des clients chargée avec succès.");
                messageLabel.setStyle("-fx-text-fill: green;"); // Couleur verte pour succès
            }
        } catch (Exception e) {
            // En cas d'erreur, afficher un message d'erreur
            messageLabel.setText("Erreur lors du chargement des clients : " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;"); // Couleur rouge pour erreur
            e.printStackTrace();
        }
    }

    private void filterClients(String searchText) {
        if (searchText.isEmpty()) {
            // Si le champ de recherche est vide, afficher tous les clients
            tableViewClients.getItems().clear();
            tableViewClients.getItems().addAll(allClients);
        } else {
            // Filtrer les clients en fonction du texte de recherche
            List<Client> filteredClients = allClients.stream()
                    .filter(client -> client.getNomClient().toLowerCase().contains(searchText.toLowerCase()) ||
                            client.getPrenomClient().toLowerCase().contains(searchText.toLowerCase()) ||
                            client.getTelephoneClient().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());

            // Mettre à jour la TableView avec les résultats filtrés
            tableViewClients.getItems().clear();
            tableViewClients.getItems().addAll(filteredClients);

            // Mettre à jour le message
            if (filteredClients.isEmpty()) {
                messageLabel.setText("Aucun client trouvé.");
                messageLabel.setStyle("-fx-text-fill: orange;"); // Couleur orange pour avertissement
            } else {
                messageLabel.setText(filteredClients.size() + " clients trouvés.");
                messageLabel.setStyle("-fx-text-fill: green;"); // Couleur verte pour succès
            }
        }
    }
}
