package com.example.playmaster.Models;

import java.time.LocalDate;

public class Tournoi {
    private int idTournoi;
    private String nomTournoi;
    private String typeTournoi;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nombreParticipants;
    private double fraisInscription;
    private String recompense;


    // Constructeur
    public Tournoi(int idTournoi, String nomTournoi, String typeTournoi, LocalDate dateDebut, LocalDate dateFin,
                   int nombreParticipants, double fraisInscription, String recompense) {
        this.idTournoi = idTournoi;
        this.nomTournoi = nomTournoi;
        this.typeTournoi = typeTournoi;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreParticipants = nombreParticipants;
        this.fraisInscription = fraisInscription;
        this.recompense = recompense;
    }

    // Getters et Setters
    public int getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }
    public String getNomTournoi() {
        return nomTournoi;
    }

    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }

    public String getTypeTournoi() {
        return typeTournoi;
    }

    public void setTypeTournoi(String typeTournoi) {
        this.typeTournoi = typeTournoi;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getNombreParticipants() {
        return nombreParticipants;
    }

    public void setNombreParticipants(int nombreParticipants) {
        this.nombreParticipants = nombreParticipants;
    }

    public double getFraisInscription() {
        return fraisInscription;
    }

    public void setFraisInscription(double fraisInscription) {
        this.fraisInscription = fraisInscription;
    }

    public String getRecompense() {
        return recompense;
    }

    public void setRecompense(String recompense) {
        this.recompense = recompense;
    }


}

