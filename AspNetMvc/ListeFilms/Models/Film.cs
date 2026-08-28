namespace ListeFilms.Models;

public class Film
{
    public int Id { get; private set; }
    public string Titre { get; private set; }
    public string Genre { get; private set; }
    public int Annee { get; private set; }
    
    public Film(int id, string titre, string genre, int annee)
    {
        Id = id;
        Annee = annee;
        Genre = genre;
        Titre = titre;
    }
}