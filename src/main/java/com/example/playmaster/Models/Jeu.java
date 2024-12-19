package com.example.playmaster.Models;

public class Jeu {
    private String nom;
    private String typeJeu;
    private String emplacement;
    private String disponibilite;
    private double prixParSession;

    // Constructeur
    public Jeu(String nom, String typeJeu, String emplacement, String disponibilite, double prixParSession) {
        this.nom = nom;
        this.typeJeu = typeJeu;
        this.emplacement = emplacement;
        this.disponibilite = disponibilite;
        this.prixParSession = prixParSession;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypeJeu() {
        return typeJeu;
    }

    public void setTypeJeu(String typeJeu) {
        this.typeJeu = typeJeu;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getDisponibilite() {

        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public double getPrixParSession() {
        return prixParSession;
    }

    public void setPrixParSession(double prixParSession) {
        this.prixParSession = prixParSession;
    }


}

