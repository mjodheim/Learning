### Exercice : CRUD complet sur l’API JsonPlaceholder
---
#### Objectif : Réaliser un CRUD complet (Create, Read, Update, Delete) avec l’API
https://jsonplaceholder.typicode.com/posts.
#### Consignes :
1. Créez un nouveau projet ASP.NET Core MVC . Nettoyez-le complètement : supprimez
tout ce qui est généré par défaut (HomeController, vues Home, Privacy, Error, etc.).
Gardez uniquement le squelette minimal.
2. Créez un dossier Models et ajoutez la classe Post avec les propriétés suivantes et les
attributs de validation :
- Id (int)
- Title (string)
- Body (string)
- UserId (int)
3. Créez le contrôleur PostController avec les cinq actions asynchrones :
- Index()
- Details(int id)
- Create() GET + POST
- Edit(int id) GET + POST
- Delete(int id) GET + POST
4. Dans le contrôleur, ajoutez une liste statique _allPosts (List<Post>) pour conserver les
modifications pendant la session.
5. Dans Program.cs, ajoutez une route custom avant la route par défaut :
6. Créez les cinq vues dans Views/Post :
- Index.cshtml (tableau avec liens Créer / Détails / Modifier / Supprimer)
- Details.cshtml
- Create.cshtml (formulaire création)
- Edit.cshtml (formulaire modification)
- Delete.cshtml (confirmation suppression)
7. Modifiez _Layout.cshtml pour inclure un menu Bootstrap avec au moins :
- Lien vers la liste des posts
- Lien vers Créer un post
8. Tous les formulaires doivent utiliser les Tag Helpers (asp-for, asp-action, asp-validationfor…).