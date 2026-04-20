namespace Domain.Entities;

public class Inscription
{
    public int TournamentId { get; set; }
    public int PlayerId { get; set; }
    public DateTime RegisteredAt { get; set; } = DateTime.Now;
    
    // Propriétés de navigation optionnelles pour faciliter l'usage en BLL
    public Player? Player { get; set; }
    public Tournament? Tournament { get; set; }
}