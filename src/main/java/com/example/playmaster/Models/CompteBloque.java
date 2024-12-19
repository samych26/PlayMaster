package com.example.playmaster.Models;

public class CompteBloque {
    private String identifiant;
    private String motDePasse;
    private String dateBlocage;
    private String raisonBlocage;

    public CompteBloque(String identifiant, String motDePasse, String dateBlocage, String raisonBlocage) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.dateBlocage = dateBlocage;
        this.raisonBlocage = raisonBlocage;
    }

    // Getters et Setters
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getDateBlocage() {
        return dateBlocage;
    }

    public void setDateBlocage(String dateBlocage) {
        this.dateBlocage = dateBlocage;
    }

    public String getRaisonBlocage() {
        return raisonBlocage;
    }

    public void setRaisonBlocage(String raisonBlocage) {
        this.raisonBlocage = raisonBlocage;
    }
}
