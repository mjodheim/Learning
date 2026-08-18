# 📚 Le Signet — Gestion d’une médiathèque

Projet Java réalisé dans le cadre d’un exercice récapitulatif sur la **programmation orientée objet**, les **collections** et les opérations **CRUD**.

L’application fonctionne en console et permet de gérer une médiathèque contenant des **livres**, **DVD** et **jeux vidéo**, ainsi que ses **membres** et leurs **emprunts**.

## Fonctionnalités

* Ajouter, rechercher, modifier et supprimer des médias
* Inscrire des membres
* Gérer les emprunts et les retours
* Détecter les retards
* Limiter le nombre d’emprunts par membre
* Afficher les médias disponibles
* Générer des statistiques sur le catalogue

## Concepts Java utilisés

* Héritage et classes abstraites
* Polymorphisme
* Encapsulation
* `enum`
* `List` et `Map`
* Surcharge de méthodes
* `equals()` et `hashCode()`
* `LocalDate`
* Méthodes et constantes `static`
* Séparation entre modèle, logique métier et interface console

## Structure

```text
mediatheque/
├── Main.java
├── models/
├── enums/
├── services/
├── utils/
└── configs/
```

La logique métier principale est centralisée dans `Mediatheque`, tandis que `Main` s’occupe des interactions avec l’utilisateur.

## Objectif

Mettre en pratique une architecture Java simple et propre en respectant les principes fondamentaux de la POO et de l’encapsulation.
