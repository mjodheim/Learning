# CRUD JSONPlaceholder — ASP.NET Core MVC

Exercice de formation consacré à la réalisation d'un **CRUD complet en ASP.NET Core MVC** en consommant l'API publique JSONPlaceholder.

Le dossier contient l'implémentation réalisée à partir des consignes ci-dessous. L'objectif est surtout de pratiquer le cycle MVC, les appels HTTP asynchrones, les formulaires Razor et la validation.

## Notions travaillées

- contrôleurs et actions MVC ;
- vues Razor et Tag Helpers ;
- modèles et validation ;
- `HttpClient` et appels asynchrones ;
- actions GET / POST ;
- routage ;
- `TempData` pour les messages de résultat ;
- opérations Create, Read, Update et Delete.

## Énoncé de l'exercice

Créer un CRUD sur les posts de `https://jsonplaceholder.typicode.com/posts`.

### Modèle `Post`

- `Id` (`int`)
- `Title` (`string`)
- `Body` (`string`)
- `UserId` (`int`)

### Contrôleur

Créer `PostController` avec :

- `Index()` ;
- `Details(int id)` ;
- `Create()` en GET et POST ;
- `Edit(int id)` en GET et POST ;
- `Delete(int id)` en GET et POST.

Les actions qui communiquent avec l'API doivent être asynchrones.

### Vues

Créer les vues correspondantes dans `Views/Post` :

- `Index.cshtml` ;
- `Details.cshtml` ;
- `Create.cshtml` ;
- `Edit.cshtml` ;
- `Delete.cshtml`.

Les formulaires utilisent les Tag Helpers (`asp-for`, `asp-action`, validation, etc.) et le layout fournit une navigation vers la liste et la création d'un post.

> JSONPlaceholder simule les opérations d'écriture : cet exercice sert à pratiquer la consommation d'une API REST depuis une application MVC, pas à fournir un stockage persistant réel.
