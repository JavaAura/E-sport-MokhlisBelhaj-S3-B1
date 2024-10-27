# eSport Tournament Manager

## Description
eSport Tournament Manager est une application Java dédiée à la gestion de tournois de jeux vidéo compétitifs. Elle propose une interface console intuitive permettant une gestion complète des tournois, équipes, joueurs et jeux.

## Fonctionnalités

### 1. Gestion des Tournois
* **Opérations de base**
  * Création et modification de tournois
  * Suivi des états (PLANIFIÉ, EN_COURS, TERMINÉ, ANNULÉ)
* **Calcul automatique de la durée**
  * Basé sur le nombre d'équipes
  * Prise en compte de la durée moyenne des matchs
  * Ajustement selon la difficulté du jeu
  * Intégration des temps de pause
  * Inclusion de la durée de cérémonie

### 2. Gestion des Équipes
* Création et modification des profils d'équipes
* Association et dissociation avec les tournois
* Gestion du classement 
* Administration des joueurs par équipe

### 3. Gestion des Joueurs
* Création et mise à jour des profils
* affichage des joueurs par équipe
### 4. Gestion des Jeux
* Administration du catalogue de jeux
* Configuration des niveaux de difficulté :
  * FACILE
  * MOYEN
  * DIFFICILE
* Paramétrage des durées moyennes de match

## Architecture

```
src/main/
├── java/
│   └── com.Esport/
│       ├── Dao/           # Couche d'accès aux données
│       ├── Modele/        # Entités JPA
│       ├── Repository/    # Couche Repository
│       ├── Service/       # Couche Service
│       ├── presentation/  # Interface utilisateur
│       └── Util/          # Classes utilitaires
└── resources/
    ├── META-INF/
    │   └── persistence.xml    # Configuration JPA
    ├── log4j.properties      # Configuration Log4j
    └── applicationContext.xml # Configuration Spring
```

## Stack Technique

### Technologies
* Java 8
* Spring Framework
* Hibernate/JPA
* Base de données H2
* Maven

### Prérequis
* JDK 8
* Maven 3.x
* IDE Java (Eclipse, IntelliJ IDEA, etc.)

## Guide de Démarrage

### Installation

1. Cloner le dépôt
```bash
git clone https://github.com/JavaAura/E-sport-MokhlisBelhaj-S3-B1.git
cd E-sport-MokhlisBelhaj-S3-B1
```

2. Compiler le projet
```bash
mvn clean install
```

3. Créer un fichier JAR exécutable
```bash
mvn clean package
```

### Exécution
1. Lancer l'application via votre IDE ou via la ligne de commande :
```bash
java -jar target/esport-tournament-manager.jar
```

### Navigation
L'application présente un menu principal avec 4 sections :

1. **Gestion des Tournois**
   * Création de tournois
   * Liste des tournois
   * Modification du statut
   * Gestion des participants

2. **Gestion des Équipes**
   * Création d'équipes
   * Modification des effectifs
   * Association aux tournois
   * Consultation du classement

3. **Gestion des Joueurs**
   * Création de profils
   * Modification des informations
   * Affiliation aux équipes

4. **Gestion des Jeux**
   * Ajout de nouveaux jeux
   * Configuration des paramètres
   * Ajustement des difficultés

## Contribution

Nous encourageons les contributions ! Voici la procédure à suivre :

1. Forker le projet
2. Créer une branche pour votre fonctionnalité
```bash
git checkout -b feature/ma-fonctionnalite
```
3. Commiter vos changements
```bash
git commit -m 'Description détaillée de la fonctionnalité'
```
4. Pousser vers la branche
```bash
git push origin feature/ma-fonctionnalite
```
5. Ouvrir une Pull Request

## Author
This project was developed by:
- [Mokhlis Belhaj](https://github.com/BelhajMokhlis)

