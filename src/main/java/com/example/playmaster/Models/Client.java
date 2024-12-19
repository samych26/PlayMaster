package com.example.playmaster.Models;

import java.time.LocalDate;

public class Client {
    private String nomClient;
    private String prenomClient;
    private LocalDate dateDeNaissanceClient;
    private String telephoneClient;

    // Constructeur
    public Client(String nomClient, String prenomClient, LocalDate dateDeNaissanceClient, String telephoneClient) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.dateDeNaissanceClient = dateDeNaissanceClient;
        this.telephoneClient = telephoneClient;
    }

    // Getters et Setters
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public LocalDate getDateDeNaissanceClient() {
        return dateDeNaissanceClient;
    }

    public void setDateDeNaissanceClient(LocalDate dateDeNaissanceClient) {
        this.dateDeNaissanceClient = dateDeNaissanceClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }


}

