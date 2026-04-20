# ChessAPI - Gestion de Tournois d'Échecs

Cette API Web permet de gérer les activités d'un club d'échecs : joueurs, tournois, inscriptions et scores.

## Architecture du Projet

Le projet suit une architecture en couches (N-Tier) pour assurer une séparation nette des responsabilités :

- **ChessAPI** : Couche de présentation (Web API). Contient les contrôleurs et la configuration de l'application.
- **BLL (Business Logic Layer)** : Contient la logique métier, les services, les DTOs et les mappers.
- **DAL (Data Access Layer)** : Gère l'accès aux données via le pattern Repository.
- **Domain** : Contient les entités de base et les énumérations partagées par toutes les couches.

## Fonctionnalités

### 1. Gestion des Joueurs
- **Création de joueur** : Pseudo (unique), Email (unique), Mot de passe (haché), Date de naissance, Genre, ELO (défaut: 1200).
- **Bonus** : Génération de mot de passe aléatoire et envoi par email.

### 2. Gestion des Tournois
- **Création** : Nom, Lieu, Min/Max joueurs, ELO Min/Max, Catégories, Statut, Ronde courante, WomenOnly, Date de fin des inscriptions.
- **Règles métier** :
    - Min joueurs <= Max joueurs.
    - ELO min <= ELO max.
    - Date de fin d'inscription > Date du jour + Min joueurs.
    - Statut initial : "En attente de joueurs".
- **Suppression** : Uniquement si le tournoi n'a pas commencé.

### 3. Consultation
- **Liste des tournois** : Affichage des 10 derniers tournois non clôturés (tri décroissant par date de mise à jour).
- **Détails d'un tournoi** : Informations complètes, liste des joueurs inscrits, et rencontres de la ronde courante.

### 4. Inscriptions
- **Inscription d'un joueur** : Vérification des contraintes (tournoi non commencé, date limite non dépassée, joueur non inscrit, capacité max, âge/catégorie, ELO, genre).
- **Désinscription** : Possible uniquement avant le début du tournoi.

### 5. Déroulement du Tournoi
- **Démarrage** : Vérification du nombre min de participants, passage à la ronde 1, génération des rencontres (Round Robin aller-retour).
- **Gestion des rencontres** : Modification des résultats pour la ronde courante uniquement.
- **Progression** : Passage à la ronde suivante si tous les matchs de la ronde actuelle sont terminés.

### 6. Classement
- **Tableau des scores** : Pour un tournoi et une ronde donnés, trié par score (Nom, Matchs, V/D/N, Score).

## Règles de Calcul du Score
- Victoire : 1 point
- Égalité : 0,5 point
- Défaite : 0 point

---
*Ce projet a été corrigé et optimisé avec l'assistance d'**Antigravity** (Gemini 3 Flash).*
