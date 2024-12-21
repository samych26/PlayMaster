-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 21 déc. 2024 à 15:10
-- Version du serveur : 8.2.0
-- Version de PHP : 8.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `playmaster`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `username`, `mot_de_passe`) VALUES
(1, 'admin1', 'admin123'),
(2, 'admin2', 'password456'),
(3, 'superadmin', 'super2024'),
(4, 'rootAdmin', 'rootSecure!'),
(5, 'adminMaster', 'MasterKey22');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nomClient` varchar(255) NOT NULL,
  `prenomClient` varchar(255) NOT NULL,
  `dateDeNaissanceClient` date NOT NULL,
  `telephoneClient` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nomClient`, `prenomClient`, `dateDeNaissanceClient`, `telephoneClient`) VALUES
(3, 'CHERFA', 'Samy', '2014-12-17', '0791306532'),
(5, 'Dupont', 'Jean', '1990-05-12', '0701234567'),
(6, 'Martin', 'Claire', '1985-08-23', '0609876543'),
(7, 'Bernard', 'Luc', '2000-01-15', '0502345678'),
(8, 'Durand', 'Sophie', '1995-03-10', '0707654321'),
(9, 'Moreau', 'Paul', '1988-11-20', '0601122334'),
(10, 'Blanc', 'Alice', '1993-07-05', '0509988776'),
(11, 'Roux', 'Maxime', '1991-02-19', '0702345678'),
(12, 'Garnier', 'Julie', '1987-04-30', '0603344556'),
(13, 'Lemoine', 'Hugo', '1994-06-21', '0504455667'),
(14, 'Faure', 'Camille', '1992-12-25', '0705566778'),
(15, 'Chevalier', 'Lucas', '1986-09-15', '0606677889'),
(16, 'Moulin', 'Emma', '1999-03-18', '0507788990'),
(17, 'Perrot', 'Nina', '1984-08-01', '0708899001'),
(18, 'Barbier', 'Antoine', '1996-11-14', '0609900012'),
(19, 'Millet', 'Eva', '1998-05-27', '0501012345'),
(20, 'Girard', 'Tom', '1997-10-10', '0702123456'),
(21, 'Renaud', 'Laura', '1989-01-05', '0603234567'),
(22, 'Lambert', 'Clara', '1990-06-18', '0504345678'),
(23, 'Fontaine', 'Nathan', '1995-03-29', '0705456789'),
(24, 'Masson', 'Elodie', '1983-12-11', '0606567890'),
(25, 'Picard', 'Alex', '1991-07-07', '0507678901'),
(26, 'Marchand', 'Sarah', '1988-02-24', '0708789012'),
(27, 'Perrin', 'Mia', '1993-10-13', '0609890123'),
(28, 'Noel', 'Theo', '1987-09-04', '0501901234'),
(29, 'Techer', 'Manon', '2000-08-22', '0702012345'),
(30, 'Julien', 'Lola', '1985-01-17', '0603123456'),
(31, 'Royer', 'Leo', '1994-05-08', '0504234567'),
(32, 'Guillot', 'Elena', '1992-03-03', '0705345678'),
(33, 'Collet', 'Adam', '1986-06-12', '0606456789'),
(34, 'Chauvet', 'Zoé', '1998-09-25', '0507567890');

-- --------------------------------------------------------

--
-- Structure de la table `comptebloquer`
--

DROP TABLE IF EXISTS `comptebloquer`;
CREATE TABLE IF NOT EXISTS `comptebloquer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `date_bloquage` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `raison_bloquage` varchar(255) DEFAULT 'Tentatives multiples incorrectes',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `comptebloquer`
--

INSERT INTO `comptebloquer` (`id`, `username`, `mot_de_passe`, `date_bloquage`, `raison_bloquage`) VALUES
(1, 'user1', 'password1', '2024-11-30 23:00:00', 'Tentatives échouées'),
(2, 'CherfaSamy', 'CherfaSamy5', '2024-12-19 09:20:01', 'Trop de tentatives échouées'),
(3, 'CherfaSamy', 'CherfaSamy5', '2024-12-19 11:46:16', 'Trop de tentatives échouées');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nomEmploye` varchar(255) NOT NULL,
  `prenomEmploye` varchar(255) NOT NULL,
  `dateDeNaissance` date NOT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `poste` varchar(100) NOT NULL,
  `salaire` decimal(10,2) NOT NULL,
  `dateEmbauche` date NOT NULL,
  `statut` varchar(50) NOT NULL,
  `tentatives` int DEFAULT '0',
  `compte_bloque` tinyint(1) DEFAULT '0',
  `date_blocage` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id`, `nomEmploye`, `prenomEmploye`, `dateDeNaissance`, `telephone`, `poste`, `salaire`, `dateEmbauche`, `statut`, `tentatives`, `compte_bloque`, `date_blocage`) VALUES
(5, 'Cherfa', 'Samy', '1990-07-12', '0612345679', 'Employé', 28000.00, '2020-09-15', 'actif', 0, 0, '2024-12-19 12:46:16'),
(4, 'Dupont', 'Jean', '1985-03-25', '0612345678', 'Responsable', 45000.00, '2015-06-01', 'actif', 0, 0, NULL),
(6, 'Gonzalez', 'Maria', '1988-11-05', '0623456780', 'Comptable', 32000.00, '2018-03-20', 'actif', 0, 0, NULL),
(7, 'Martin', 'Sophie', '1995-01-18', '0623456781', 'Assistante', 24000.00, '2021-11-12', 'actif', 0, 0, NULL),
(8, 'Leroy', 'Paul', '1983-04-10', '0623456782', 'Directeur', 60000.00, '2010-05-05', 'actif', 0, 0, NULL),
(9, 'Lopez', 'Ana', '1992-08-23', '0623456783', 'Employée', 26000.00, '2019-07-22', 'actif', 0, 0, NULL),
(10, 'Durand', 'Marc', '1987-02-14', '0623456784', 'Manager', 37000.00, '2017-10-30', 'actif', 0, 0, NULL),
(11, 'Morel', 'Clara', '1994-12-01', '0623456785', 'RH', 30000.00, '2020-01-20', 'actif', 0, 0, NULL),
(12, 'Laurent', 'Emma', '1998-06-30', '0623456786', 'Stagiaire', 15000.00, '2023-04-10', 'actif', 0, 0, NULL),
(13, 'Petit', 'Lucas', '1991-09-08', '0623456787', 'Technicien', 29000.00, '2019-02-25', 'actif', 0, 0, NULL),
(14, 'Cherfa', 'test', '2024-12-11', '0791306532', 'test', 12.00, '2024-12-18', 'Actif', 0, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `jeu`
--

DROP TABLE IF EXISTS `jeu`;
CREATE TABLE IF NOT EXISTS `jeu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `typejeu` varchar(100) NOT NULL,
  `emplacement` varchar(255) NOT NULL,
  `disponibilite` varchar(255) DEFAULT NULL,
  `prixparsession` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `jeu`
--

INSERT INTO `jeu` (`id`, `nom`, `typejeu`, `emplacement`, `disponibilite`, `prixparsession`) VALUES
(8, 'Fléchettes', 'Arcade', 'Zone C', 'Non', 15.00),
(7, 'Billard', 'test', 'Zone B', 'Oui', 30.00),
(6, 'Bowling', 'Sport', 'Zone A', 'Oui', 50.00),
(9, 'Air Hockey', 'Arcade', 'Zone D', 'Oui', 20.00),
(10, 'Karaoké', 'Musique', 'Zone E', 'Oui', 40.00),
(11, 'Babyfoot', 'Table', 'Zone F', 'Oui', 25.00),
(12, 'Simulateur de course', 'Simulateur', 'Zone G', 'Non', 60.00),
(13, 'Escape Game', 'Aventure', 'Zone H', 'Oui', 100.00),
(14, 'VR Shooter', 'Réalité Virtuelle', 'Zone I', 'Oui', 80.00),
(15, 'Ping-Pong', 'Sport', 'Zone J', 'Oui', 20.00),
(16, 'Jeu de tir au panier', 'Arcade', 'Zone A', 'Non', 10.00),
(17, 'Dance Dance Revolution', 'Musique', 'Zone B', 'Oui', 30.00),
(18, 'Simulateur de vol', 'Simulateur', 'Zone C', 'Non', 70.00),
(19, 'Tir au pistolet laser', 'Arcade', 'Zone D', 'Oui', 50.00),
(20, 'Mini-Golf', 'Sport', 'Zone E', 'Oui', 35.00),
(21, 'Mur de escalade', 'Sport', 'Zone F', 'Non', 90.00),
(22, 'Jeu de marteau', 'Arcade', 'Zone G', 'Oui', 15.00),
(23, 'Bataille de Nerf', 'Aventure', 'Zone H', 'Oui', 75.00),
(24, 'Lancer de haches', 'Aventure', 'Zone I', 'Non', 65.00),
(25, 'Trampoline', 'Sport', 'Zone J', 'Oui', 50.00);

-- --------------------------------------------------------

--
-- Structure de la table `participants`
--

DROP TABLE IF EXISTS `participants`;
CREATE TABLE IF NOT EXISTS `participants` (
  `id_participant` int NOT NULL AUTO_INCREMENT,
  `id_tournoi` int NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `age` int NOT NULL,
  `email` varchar(150) NOT NULL,
  PRIMARY KEY (`id_participant`),
  KEY `fk_tournoi` (`id_tournoi`)
) ENGINE=MyISAM AUTO_INCREMENT=164 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `participants`
--

INSERT INTO `participants` (`id_participant`, `id_tournoi`, `nom`, `prenom`, `age`, `email`) VALUES
(4, 8, 'Dupont', 'Pierre', 25, 'pierre.dupont@example.com'),
(5, 8, 'Martin', 'Claire', 28, 'claire.martin@example.com'),
(6, 8, 'Durand', 'Julien', 30, 'julien.durand@example.com'),
(7, 8, 'Lemoine', 'Sophie', 22, 'sophie.lemoine@example.com'),
(8, 8, 'Tanguy', 'Maxime', 27, 'maxime.tanguy@example.com'),
(9, 8, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(10, 8, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(11, 8, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com'),
(12, 9, 'Girard', 'Julie', 23, 'julie.girard@example.com'),
(13, 9, 'Petit', 'Eric', 31, 'eric.petit@example.com'),
(14, 9, 'Robert', 'Emma', 27, 'emma.robert@example.com'),
(15, 9, 'Dufresne', 'David', 29, 'david.dufresne@example.com'),
(16, 9, 'Joubert', 'Sébastien', 25, 'sebastien.joubert@example.com'),
(17, 9, 'Carre', 'Isabelle', 24, 'isabelle.carre@example.com'),
(18, 9, 'Michel', 'François', 32, 'francois.michel@example.com'),
(19, 9, 'Gauthier', 'Caroline', 28, 'caroline.gauthier@example.com'),
(20, 10, 'Leclerc', 'Vincent', 30, 'vincent.leclerc@example.com'),
(21, 10, 'Lemoine', 'Alice', 26, 'alice.lemoine@example.com'),
(22, 10, 'Blanc', 'Paul', 33, 'paul.blanc@example.com'),
(23, 10, 'Marchand', 'Sylvie', 35, 'sylvie.marchand@example.com'),
(24, 10, 'Dupont', 'Marc', 29, 'marc.dupont@example.com'),
(25, 10, 'Martin', 'Eva', 27, 'eva.martin@example.com'),
(26, 10, 'Durand', 'Olivier', 31, 'olivier.durand@example.com'),
(27, 10, 'Tanguy', 'Julie', 24, 'julie.tanguy@example.com'),
(28, 11, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(29, 11, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(30, 11, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com'),
(31, 11, 'Girard', 'Julie', 23, 'julie.girard@example.com'),
(32, 11, 'Petit', 'Eric', 31, 'eric.petit@example.com'),
(33, 11, 'Robert', 'Emma', 27, 'emma.robert@example.com'),
(34, 11, 'Dufresne', 'David', 29, 'david.dufresne@example.com'),
(35, 11, 'Joubert', 'Sébastien', 25, 'sebastien.joubert@example.com'),
(36, 12, 'Carre', 'Isabelle', 24, 'isabelle.carre@example.com'),
(37, 12, 'Michel', 'François', 32, 'francois.michel@example.com'),
(38, 12, 'Gauthier', 'Caroline', 28, 'caroline.gauthier@example.com'),
(39, 12, 'Leclerc', 'Vincent', 30, 'vincent.leclerc@example.com'),
(40, 12, 'Lemoine', 'Alice', 26, 'alice.lemoine@example.com'),
(41, 12, 'Blanc', 'Paul', 33, 'paul.blanc@example.com'),
(42, 12, 'Marchand', 'Sylvie', 35, 'sylvie.marchand@example.com'),
(43, 12, 'Dupont', 'Marc', 29, 'marc.dupont@example.com'),
(44, 13, 'Martin', 'Eva', 27, 'eva.martin@example.com'),
(45, 13, 'Durand', 'Olivier', 31, 'olivier.durand@example.com'),
(46, 13, 'Tanguy', 'Julie', 24, 'julie.tanguy@example.com'),
(47, 13, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(48, 13, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(49, 13, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com'),
(50, 13, 'Girard', 'Julie', 23, 'julie.girard@example.com'),
(51, 13, 'Petit', 'Eric', 31, 'eric.petit@example.com'),
(52, 14, 'Robert', 'Emma', 27, 'emma.robert@example.com'),
(53, 14, 'Dufresne', 'David', 29, 'david.dufresne@example.com'),
(54, 14, 'Joubert', 'Sébastien', 25, 'sebastien.joubert@example.com'),
(55, 14, 'Carre', 'Isabelle', 24, 'isabelle.carre@example.com'),
(56, 14, 'Michel', 'François', 32, 'francois.michel@example.com'),
(57, 14, 'Gauthier', 'Caroline', 28, 'caroline.gauthier@example.com'),
(58, 14, 'Leclerc', 'Vincent', 30, 'vincent.leclerc@example.com'),
(59, 14, 'Lemoine', 'Alice', 26, 'alice.lemoine@example.com'),
(60, 15, 'Blanc', 'Paul', 33, 'paul.blanc@example.com'),
(61, 15, 'Marchand', 'Sylvie', 35, 'sylvie.marchand@example.com'),
(62, 15, 'Dupont', 'Marc', 29, 'marc.dupont@example.com'),
(63, 15, 'Martin', 'Eva', 27, 'eva.martin@example.com'),
(64, 15, 'Durand', 'Olivier', 31, 'olivier.durand@example.com'),
(65, 15, 'Tanguy', 'Julie', 24, 'julie.tanguy@example.com'),
(66, 15, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(67, 15, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(68, 16, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com'),
(69, 16, 'Girard', 'Julie', 23, 'julie.girard@example.com'),
(70, 16, 'Petit', 'Eric', 31, 'eric.petit@example.com'),
(71, 16, 'Robert', 'Emma', 27, 'emma.robert@example.com'),
(72, 16, 'Dufresne', 'David', 29, 'david.dufresne@example.com'),
(73, 16, 'Joubert', 'Sébastien', 25, 'sebastien.joubert@example.com'),
(74, 16, 'Carre', 'Isabelle', 24, 'isabelle.carre@example.com'),
(75, 16, 'Michel', 'François', 32, 'francois.michel@example.com'),
(76, 17, 'Gauthier', 'Caroline', 28, 'caroline.gauthier@example.com'),
(77, 17, 'Leclerc', 'Vincent', 30, 'vincent.leclerc@example.com'),
(78, 17, 'Lemoine', 'Alice', 26, 'alice.lemoine@example.com'),
(79, 17, 'Blanc', 'Paul', 33, 'paul.blanc@example.com'),
(80, 17, 'Marchand', 'Sylvie', 35, 'sylvie.marchand@example.com'),
(81, 17, 'Dupont', 'Marc', 29, 'marc.dupont@example.com'),
(82, 17, 'Martin', 'Eva', 27, 'eva.martin@example.com'),
(83, 17, 'Durand', 'Olivier', 31, 'olivier.durand@example.com'),
(84, 18, 'Tanguy', 'Julie', 24, 'julie.tanguy@example.com'),
(85, 18, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(86, 18, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(87, 18, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com'),
(88, 18, 'Girard', 'Julie', 23, 'julie.girard@example.com'),
(89, 18, 'Petit', 'Eric', 31, 'eric.petit@example.com'),
(90, 18, 'Robert', 'Emma', 27, 'emma.robert@example.com'),
(91, 18, 'Dufresne', 'David', 29, 'david.dufresne@example.com'),
(92, 19, 'Joubert', 'Sébastien', 25, 'sebastien.joubert@example.com'),
(93, 19, 'Carre', 'Isabelle', 24, 'isabelle.carre@example.com'),
(94, 19, 'Michel', 'François', 32, 'francois.michel@example.com'),
(95, 19, 'Gauthier', 'Caroline', 28, 'caroline.gauthier@example.com'),
(96, 19, 'Leclerc', 'Vincent', 30, 'vincent.leclerc@example.com'),
(97, 19, 'Lemoine', 'Alice', 26, 'alice.lemoine@example.com'),
(98, 19, 'Blanc', 'Paul', 33, 'paul.blanc@example.com'),
(99, 19, 'Marchand', 'Sylvie', 35, 'sylvie.marchand@example.com'),
(100, 20, 'Dupont', 'Marc', 29, 'marc.dupont@example.com'),
(101, 20, 'Martin', 'Eva', 27, 'eva.martin@example.com'),
(102, 20, 'Durand', 'Olivier', 31, 'olivier.durand@example.com'),
(103, 20, 'Tanguy', 'Julie', 24, 'julie.tanguy@example.com'),
(104, 20, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(105, 20, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(106, 20, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com'),
(107, 20, 'Girard', 'Julie', 23, 'julie.girard@example.com'),
(108, 21, 'Petit', 'Eric', 31, 'eric.petit@example.com'),
(109, 21, 'Robert', 'Emma', 27, 'emma.robert@example.com'),
(110, 21, 'Dufresne', 'David', 29, 'david.dufresne@example.com'),
(111, 21, 'Joubert', 'Sébastien', 25, 'sebastien.joubert@example.com'),
(112, 21, 'Carre', 'Isabelle', 24, 'isabelle.carre@example.com'),
(113, 21, 'Michel', 'François', 32, 'francois.michel@example.com'),
(114, 21, 'Gauthier', 'Caroline', 28, 'caroline.gauthier@example.com'),
(115, 21, 'Leclerc', 'Vincent', 30, 'vincent.leclerc@example.com'),
(116, 22, 'Lemoine', 'Alice', 26, 'alice.lemoine@example.com'),
(117, 22, 'Blanc', 'Paul', 33, 'paul.blanc@example.com'),
(118, 22, 'Marchand', 'Sylvie', 35, 'sylvie.marchand@example.com'),
(119, 22, 'Dupont', 'Marc', 29, 'marc.dupont@example.com'),
(120, 22, 'Martin', 'Eva', 27, 'eva.martin@example.com'),
(121, 22, 'Durand', 'Olivier', 31, 'olivier.durand@example.com'),
(122, 22, 'Tanguy', 'Julie', 24, 'julie.tanguy@example.com'),
(123, 22, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(124, 23, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(125, 23, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com'),
(126, 23, 'Girard', 'Julie', 23, 'julie.girard@example.com'),
(127, 23, 'Petit', 'Eric', 31, 'eric.petit@example.com'),
(128, 23, 'Robert', 'Emma', 27, 'emma.robert@example.com'),
(129, 23, 'Dufresne', 'David', 29, 'david.dufresne@example.com'),
(130, 23, 'Joubert', 'Sébastien', 25, 'sebastien.joubert@example.com'),
(131, 23, 'Carre', 'Isabelle', 24, 'isabelle.carre@example.com'),
(132, 24, 'Michel', 'François', 32, 'francois.michel@example.com'),
(133, 24, 'Gauthier', 'Caroline', 28, 'caroline.gauthier@example.com'),
(134, 24, 'Leclerc', 'Vincent', 30, 'vincent.leclerc@example.com'),
(135, 24, 'Lemoine', 'Alice', 26, 'alice.lemoine@example.com'),
(136, 24, 'Blanc', 'Paul', 33, 'paul.blanc@example.com'),
(137, 24, 'Marchand', 'Sylvie', 35, 'sylvie.marchand@example.com'),
(138, 24, 'Dupont', 'Marc', 29, 'marc.dupont@example.com'),
(139, 24, 'Martin', 'Eva', 27, 'eva.martin@example.com'),
(140, 25, 'Durand', 'Olivier', 31, 'olivier.durand@example.com'),
(141, 25, 'Tanguy', 'Julie', 24, 'julie.tanguy@example.com'),
(142, 25, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(143, 25, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(144, 25, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com'),
(145, 25, 'Girard', 'Julie', 23, 'julie.girard@example.com'),
(146, 25, 'Petit', 'Eric', 31, 'eric.petit@example.com'),
(147, 25, 'Robert', 'Emma', 27, 'emma.robert@example.com'),
(148, 26, 'Dufresne', 'David', 29, 'david.dufresne@example.com'),
(149, 26, 'Joubert', 'Sébastien', 25, 'sebastien.joubert@example.com'),
(150, 26, 'Carre', 'Isabelle', 24, 'isabelle.carre@example.com'),
(151, 26, 'Michel', 'François', 32, 'francois.michel@example.com'),
(152, 26, 'Gauthier', 'Caroline', 28, 'caroline.gauthier@example.com'),
(153, 26, 'Leclerc', 'Vincent', 30, 'vincent.leclerc@example.com'),
(154, 26, 'Lemoine', 'Alice', 26, 'alice.lemoine@example.com'),
(155, 26, 'Blanc', 'Paul', 33, 'paul.blanc@example.com'),
(156, 27, 'Marchand', 'Sylvie', 35, 'sylvie.marchand@example.com'),
(157, 27, 'Dupont', 'Marc', 29, 'marc.dupont@example.com'),
(158, 27, 'Martin', 'Eva', 27, 'eva.martin@example.com'),
(159, 27, 'Durand', 'Olivier', 31, 'olivier.durand@example.com'),
(160, 27, 'Tanguy', 'Julie', 24, 'julie.tanguy@example.com'),
(161, 27, 'Moreau', 'Lucas', 24, 'lucas.moreau@example.com'),
(162, 27, 'Benoit', 'Amélie', 26, 'amelie.benoit@example.com'),
(163, 27, 'Rousseau', 'Nicolas', 29, 'nicolas.rousseau@example.com');

-- --------------------------------------------------------

--
-- Structure de la table `tournoi`
--

DROP TABLE IF EXISTS `tournoi`;
CREATE TABLE IF NOT EXISTS `tournoi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `nombre_participants` int DEFAULT NULL,
  `frais_inscription` decimal(10,2) DEFAULT NULL,
  `recompense` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `tournoi`
--

INSERT INTO `tournoi` (`id`, `nom`, `type`, `date_debut`, `date_fin`, `nombre_participants`, `frais_inscription`, `recompense`) VALUES
(8, 'Tournoi Bowling', 'Sport', '2024-01-15', '2024-01-16', 16, 500.00, 'Trophée et 1000€'),
(9, 'Championnat Billard', 'Table', '2024-02-10', '2024-02-12', 32, 300.00, 'Trophée et 500€'),
(10, 'Fléchettes Pro', 'Arcade', '2024-03-01', '2024-03-02', 20, 150.00, '300€'),
(11, 'Air Hockey Cup', 'Arcade', '2024-03-15', '2024-03-16', 24, 200.00, '400€'),
(12, 'Challenge Karaoké', 'Musique', '2024-04-05', '2024-04-05', 10, 50.00, '150€'),
(13, 'Babyfoot Battle', 'Table', '2024-04-20', '2024-04-21', 18, 250.00, '300€'),
(14, 'Simulateur de course Open', 'Simulateur', '2024-05-01', '2024-05-03', 12, 600.00, '1000€'),
(15, 'Escape Game Championship', 'Aventure', '2024-06-10', '2024-06-12', 8, 800.00, '1500€'),
(16, 'VR Shooter Masters', 'Réalité Virtuelle', '2024-07-01', '2024-07-02', 14, 500.00, '800€'),
(17, 'Ping-Pong Tournament', 'Sport', '2024-07-15', '2024-07-16', 32, 100.00, '200€'),
(18, 'Jeu de tir au panier Elite', 'Arcade', '2024-08-05', '2024-08-06', 25, 120.00, '300€'),
(19, 'Dance Revolution Challenge', 'Musique', '2024-08-20', '2024-08-20', 15, 80.00, '200€'),
(20, 'Simulateur de vol Open', 'Simulateur', '2024-09-01', '2024-09-03', 10, 700.00, '1200€'),
(21, 'Laser Tag Battle', 'Arcade', '2024-09-15', '2024-09-16', 30, 400.00, '600€'),
(22, 'Mini-Golf Open', 'Sport', '2024-10-01', '2024-10-03', 20, 150.00, '400€'),
(23, 'Mur de escalade Challenge', 'Sport', '2024-10-20', '2024-10-21', 12, 500.00, '700€'),
(24, 'Marteau Power Tour', 'Arcade', '2024-11-05', '2024-11-05', 15, 100.00, '200€'),
(25, 'Bataille Nerf Masters', 'Aventure', '2024-11-15', '2024-11-17', 10, 400.00, '700€'),
(26, 'Lancer de haches Elite', 'Aventure', '2024-12-01', '2024-12-02', 18, 300.00, '500€'),
(27, 'Trampoline Challenge', 'Sport', '2024-12-15', '2024-12-16', 16, 250.00, '400€');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
