namespace DAL.Models;

public class Game
{
    public int Id { get; set; }
    public string Titre { get; set; } = string.Empty;
    public DateTime DateSortie { get; set; }
    public int EditeurId { get; set; }

    public Editeur? Editor { get; set; }                // One-to-Many
    public DetailsJeu Details { get; set; }               // One-to-One
    public List<Genre> Genres { get; set; } = new();    // Many-to-Many

}