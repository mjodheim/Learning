namespace DAL.Models;

public class Genre
{
    public int Id { get; set; }
    public string Nom { get; set; } = string.Empty;

    public List<Game> Games { get; set; } = new();
}