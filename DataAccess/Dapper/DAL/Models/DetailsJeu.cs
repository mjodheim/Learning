namespace DAL.Models;

public class DetailsJeu
{
    public int JeuId { get; set; }
    public string? Description { get; set; }
    public string? ScoreMetacritic { get; set; }
    public int? VentesMillions { get; set; }

    public Game  Game { get; set; }
    
    public DetailsJeu()
    {
        
    }
}