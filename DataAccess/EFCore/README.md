# Entity Framework Core — exercices

Petite série d'exercices consacrée à la découverte d'**Entity Framework Core**, des migrations et des relations entre entités.

## Exercice 1 — Voitures

Créer une base de données contenant une entité `Auto` / voiture avec notamment :

- un identifiant ;
- une marque ;
- un modèle.

Étapes :

1. créer une première migration ;
2. ajouter ensuite une plaque d'immatriculation ;
3. créer une seconde migration ;
4. ajouter les contraintes utiles au modèle.

## Exercice 2 — Garages

Ajouter une entité `Garage` comportant un identifiant, un nom et une adresse.

Un garage peut contenir plusieurs voitures.

Objectifs :

1. modéliser la relation entre `Garage` et les voitures ;
2. créer une nouvelle migration ;
3. enregistrer un garage contenant plusieurs voitures ;
4. charger les voitures associées au garage ;
5. effectuer une recherche sur la plaque.

L'exercice introduit notamment le chargement des relations avec `Include`.

## Notions travaillées

- `DbContext` ;
- entités et relations ;
- migrations ;
- contraintes de modèle ;
- navigation entre entités ;
- requêtes LINQ ;
- chargement avec `Include`.
