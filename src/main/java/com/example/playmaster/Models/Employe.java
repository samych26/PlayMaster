package com.example.playmaster.Models;

import java.time.LocalDate;

public class Employe {
    private String nomEmploye;
    private String prenomEmploye;
    private LocalDate dateDeNaissanceEmploye;
    private String telephoneEmploye;
    private String poste;
    private double salaire;
    private LocalDate dateEmbauche;
    private String statut;

    // Constructeur
    public Employe(String nomEmploye, String prenomEmploye, LocalDate dateDeNaissanceEmploye, String telephoneEmploye, String poste, double salaire, LocalDate dateEmbauche, String statut) {
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.dateDeNaissanceEmploye = dateDeNaissanceEmploye;
        this.telephoneEmploye = telephoneEmploye;
        this.poste = poste;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
        this.statut = statut;
    }

    // Getters et Setters
    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissanceEmploye;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissanceEmploye = dateDeNaissance;
    }

    public String getTelephone() {
        return telephoneEmploye;
    }

    public void setTelephone(String telephone) {
        this.telephoneEmploye = telephone;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

}
