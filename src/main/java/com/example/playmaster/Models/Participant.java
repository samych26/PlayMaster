package com.example.playmaster.Models;


    public class Participant {
        private int idParticipant; // Identifiant unique pour chaque participant
        private int idTournoi;     // Référence au tournoi
        private String nom;
        private String prenom;
        private int age;
        private String email;

        // Constructeur
        public Participant(String nom, String prenom, int age, String email) {
            this.idParticipant = idParticipant;
            this.idTournoi = idTournoi;
            this.nom = nom;
            this.prenom = prenom;
            this.age = age;
            this.email = email;
        }

        // Getters et Setters
        public int getIdParticipant() {
            return idParticipant;
        }

        public void setIdParticipant(int idParticipant) {
            this.idParticipant = idParticipant;
        }

        public int getIdTournoi() {
            return idTournoi;
        }

        public void setIdTournoi(int idTournoi) {
            this.idTournoi = idTournoi;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }
        public String getPrenom() {
        return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }
        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }



