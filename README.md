# PlayMaster 🎮

**PlayMaster** est une application de gestion pour centres de jeux. Elle permet de gérer efficacement les clients, les employés, les tournois, et les jeux disponibles dans le centre. Développée en **Java** avec JavaFX pour l'interface utilisateur, cette application fournit une solution complète et intuitive pour les gestionnaires de centres de divertissement.

---

## 📋 Fonctionnalités principales

- **Gestion des clients :**
  - Enregistrer, modifier et supprimer des clients.
  - Afficher une liste détaillée des clients.

- **Gestion des jeux :**
  - Ajouter, modifier et supprimer des jeux.
  - Afficher une liste des jeux disponibles.

- **Gestion des tournois :**
  - Créer et planifier des tournois.
  - Inscrire des participants.
  - Afficher l'historique des tournois.

- **Gestion des employés :**
  - Ajouter, modifier et supprimer des employés.
  - Afficher une liste des employés.

---

## 🛠️ Technologies utilisées

- **Langage :** Java
- **Interface utilisateur :** JavaFX
- **Base de données :** MySQL
- **Outils de développement :** IntelliJ IDEA
- **Système de versionnement :** Git / GitHub

---

## 🚀 Prérequis pour exécuter le projet

1. **Java Development Kit (JDK)** - Version 17 ou supérieure.
2. **JavaFX SDK** - Intégré au projet.
3. **MySQL** - Pour la gestion de la base de données.
4. **Git** - Pour cloner le projet.
5. **IDE recommandé :** IntelliJ IDEA.

## ⚙️ Installation et configuration

### Étape 1 : Cloner le projet
git clone https://github.com/samych26/PlayMaster.git
cd PlayMaster

###Étape 2 : Importer dans IntelliJ IDEA
Ouvrez IntelliJ IDEA.
Importez le dossier du projet.
Configurez le SDK pour utiliser Java 17.

###Étape 3 : Configurer la base de données
Créez une base de données dans MySQL.
Importez les scripts de la base de données situés dans le dossier src/main/resources/BaseDeDonnees.
Configurez les informations de connexion dans le fichier src/main/resources/config.properties :
-- db.url=jdbc:mysql://localhost:3306/playmaster
-- db.username=root
-- db.password=""

Étape 4 : Exécuter l'application
Compilez le projet.
Exécutez la classe principale App.java.


📂 Structure du projet
src/main/java : Contient le code source Java.
src/main/resources : Contient les fichiers de configuration, les fichiers FXML, et les ressources (images, styles, etc.).
src/main/resources/BaseDeDonnees : Scripts SQL pour la base de données.
out/artifacts : Contient le fichier .jar généré.

🌟 Remerciements
Merci d'utiliser PlayMaster ! Votre soutien est précieux pour l'amélioration continue de ce projet.


