## Exercice : Gestion d’une liste de films
Reprendre et appliquer tous les concepts vus aujourd’hui sur un nouveau thème : les
films. Créer une petite application MVC qui affiche une liste de films, filtre par genre via
une URL propre, affiche les détails d’un film et montre une page récapitulative.
Consignes obligatoires
1. Créez un nouveau projet ASP.NET Core MVC (.NET 6 ou 8). Nettoyez-le
   complètement : supprimez tout ce qui est généré par défaut (HomeController,
   vues Home, Privacy, Error, etc.). Gardez uniquement le squelette minimal :
   Program.cs, appsettings.json, launchSettings.json, _Layout.cshtml, wwwroot
   (vide ou avec un petit style si vous voulez).
2. Créez un dossier Models et ajoutez une classe Film avec les propriétés suivantes
   :
   - Id (int)
   - Titre (string)
   - Genre (string)
   - Annee (int)
3. Créez un contrôleur FilmController avec les trois actions suivantes :
   - Index(string? genre = null) : affiche tous les films ou ceux correspondant
   au genre passé en paramètre
   - Details(int id) : affiche les détails d’un film spécifique
   - APropos() : affiche une page avec des statistiques simples et un éventuel
   message persistant
4. Dans le contrôleur, créez une liste statique de films (au moins 6 éléments)
   directement dans la classe.
5. Dans Program.cs, ajoutez une route custom avant la route par défaut :
   - Pattern : "films/{genre}"
   - Cette route doit mapper vers FilmController.Index avec le paramètre genre
6. Créez les trois vues correspondantes dans Views/Film :
   - Index.cshtml : affiche la liste des films avec une boucle
   - Details.cshtml : affiche les informations d’un film
   - APropos.cshtml : affiche un récapitulatif
7. Utilisez obligatoirement les trois méthodes de passage de données :
   - ViewData
   - ViewBag
   - TempData (au moins une fois, par exemple pour un message qui survit
   après redirection vers APropos)
8. Modifiez _Layout.cshtml pour inclure un menu de navigation simple avec au
   moins :
   - Lien vers la liste complète
   - Lien vers une catégorie spécifique (exemple : Action)
   - Lien vers la page À propos
   
---
Bonus (à réaliser dans l’ordre si vous terminez tôt) A. Dans la vue Index, ajoutez un
lien qui recharge la page avec la liste triée par titre croissant (A → Z). B. Ajoutez une
action de suppression :
- GET : page de confirmation pour un film donné
- POST : supprime le film de la liste, affiche un message de succès via TempData et
redirige vers Index