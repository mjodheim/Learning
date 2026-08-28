# Liste de films — ASP.NET Core MVC

Exercice de formation destiné à pratiquer les bases d'**ASP.NET Core MVC** sur un petit catalogue de films conservé en mémoire.

L'application met en pratique le routage, les contrôleurs, les vues Razor et les différents mécanismes de passage de données vers les vues.

## Notions travaillées

- modèle MVC ;
- contrôleurs et vues Razor ;
- route personnalisée ;
- filtrage par paramètre d'URL ;
- affichage d'une liste et d'une page de détails ;
- `ViewData`, `ViewBag` et `TempData` ;
- navigation via le layout ;
- manipulation d'une collection en mémoire.

## Énoncé de l'exercice

Créer une application MVC autour d'une liste de films.

### Modèle `Film`

- `Id` (`int`)
- `Titre` (`string`)
- `Genre` (`string`)
- `Annee` (`int`)

### Actions principales

`FilmController` doit proposer :

- `Index(string? genre = null)` pour afficher tous les films ou filtrer par genre ;
- `Details(int id)` pour afficher un film ;
- `APropos()` pour présenter quelques informations récapitulatives.

Le contrôleur utilise une liste statique d'au moins six films.

### Routage

Une route personnalisée permet d'accéder directement à une catégorie :

```text
films/{genre}
```

### Vues

Créer :

- `Index.cshtml` ;
- `Details.cshtml` ;
- `APropos.cshtml`.

L'exercice demande également d'utiliser au moins une fois `ViewData`, `ViewBag` et `TempData`, puis d'ajouter une navigation simple dans `_Layout.cshtml`.

## Bonus de l'énoncé

- tri alphabétique de la liste ;
- suppression d'un film avec confirmation GET/POST et message via `TempData`.

> Ce projet est volontairement simple : son objectif est de comprendre le fonctionnement de MVC avant d'introduire une base de données ou une architecture plus complète.
