# Gestion d'une banque — Java OOP

Exercice console de **programmation orientée objet en Java** autour d'une petite application bancaire.

Le projet permet de manipuler plusieurs titulaires et plusieurs types de comptes, puis d'effectuer des opérations depuis des menus console.

## Fonctionnalités présentes

- gestion de titulaires ;
- création et regroupement de comptes par titulaire ;
- comptes courants et comptes épargne ;
- génération d'un numéro de compte ;
- dépôts et retraits ;
- affichage des comptes ;
- calcul des avoirs d'un titulaire ;
- calcul et application des intérêts ;
- règles différentes selon le type de compte.

## Modèle objet

`Compte` est une **classe abstraite** qui contient les éléments communs aux comptes : numéro, titulaire, solde, dépôt, retrait, description et application des intérêts.

Deux spécialisations sont utilisées :

- `CompteCourant` pour le compte disposant d'une ligne de crédit ;
- `CompteEpargne` pour le compte épargne et ses règles spécifiques.

Chaque sous-classe fournit notamment son type de compte, son montant retirable, son taux et son calcul d'intérêts. La classe mère conserve le comportement commun.

## Organisation actuelle

```text
Banque/
├── Banque.java
├── Personne.java
├── Gestion/
│   ├── Compte.java
│   ├── CompteCourant.java
│   ├── CompteEpargne.java
│   └── Gestion.java
├── Operations/
│   ├── Afficher.java
│   ├── Depot.java
│   ├── Retrait.java
│   ├── Interets.java
│   └── Operations.java
├── Utils/
└── README.md
```

La classe `Banque` conserve les comptes dans une collection associant chaque `Personne` à sa liste de comptes. Elle fournit également le calcul des avoirs, l'application globale des intérêts et la génération des numéros de compte.

## Menus

L'exercice est accessible depuis le `Main` du projet OOP avec l'option **3. Banque**.

Le menu Banque sépare ensuite :

1. **Gestion** — opérations liées aux titulaires et aux comptes ;
2. **Opérations** — affichage, dépôt, retrait et intérêts.

Quelques comptes sont créés au démarrage afin de pouvoir tester directement les différents menus.

## Concepts travaillés

- encapsulation ;
- héritage ;
- classe abstraite ;
- polymorphisme ;
- redéfinition de méthodes ;
- collections (`HashMap`, `List`) ;
- composition entre objets ;
- `equals()` et `hashCode()` ;
- exceptions pour certaines validations ;
- séparation de la logique métier et des menus console.

## Objectif pédagogique

L'objectif principal est de faire porter les règles propres à un type de compte par l'objet concerné, plutôt que de multiplier les tests de type dans le reste de l'application.

Ce dossier reste un **exercice d'apprentissage** : il ne cherche pas à modéliser un véritable système bancaire ni ses contraintes de sécurité ou de persistance.
