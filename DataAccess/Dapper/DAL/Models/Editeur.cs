namespace DAL.Models;

public class Editeur
{
    public int Id { get; set; }
    public string Nom { get; set; } = string.Empty;
    public string? Pays { get; set; }
    
    public List<Game> Games { get; set; } = new();
}