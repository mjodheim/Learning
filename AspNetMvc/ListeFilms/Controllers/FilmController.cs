using Microsoft.AspNetCore.Mvc;
using ListeFilms.Models;

namespace ListeFilms.Controllers;

public class FilmController : Controller
{
    public readonly List<Film> Films = new()
    {
        new Film(0, "Inception", "Science-fiction", 2010),
        new Film(1, "Parasite", "Drame", 2019),
        new Film(2, "Interstellar", "Science-fiction", 2014),
        new Film(3, "Gladiator", "Drame", 2000),
        new Film(4, "The Dark Knight", "Super-hero", 2008),
        new Film(5, "Le Seigneur des Anneaux : La Communauté de l’Anneau", "Fantasy", 2001),
    };

    public IActionResult Index(string? genre = null)
    {
        List<Film> listeFiltree = string.IsNullOrEmpty(genre)
            ? Films
            : Films.Where(f => f.Genre.Equals(genre, StringComparison.OrdinalIgnoreCase)).ToList();

        ViewData["GenreActif"] = genre ?? "Tous";
        return View(listeFiltree);
    }

    public IActionResult Details(int id)
    {
        Film? film = Films.FirstOrDefault(f => f.Id == id);
        if (film is null)
            return NotFound();

        return View(film);
    }

    public IActionResult APropos()
    {
        ViewData["NbFilms"] = Films.Count;
        ViewData["Genres"] = Films.Select(f => f.Genre).Distinct().Order().ToList();
        TempData["Message"] = "Page consultée avec succès !";
        return View();
    }
}