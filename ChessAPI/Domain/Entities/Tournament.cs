using Domain.Enums;

namespace Domain.Entities;

public class Tournament
{
    public int Id { get; set; }
    public string Name { get; set; } = string.Empty;
    public string Location { get; set; } = string.Empty;
    public int MinPlayers { get; set; } = 2;
    public int MaxPlayers { get; set; } 
    public int MinElo { get; set; } = 1000;
    public int MaxElo { get; set; }
    public TournamentStatus Status { get; set; } = TournamentStatus.WaitingForPlayers;
    public int CurrentRound { get; set; } = 0;
    public bool WomenOnly { get; set; }
    public DateTime RegistrationDeadline { get; set; }
    public DateTime CreatedAt { get; set; } = DateTime.Now;
    public DateTime UpdatedAt { get; set; } = DateTime.Now;

    // Ajout des relations pour correspondre au diagramme
    public List<Category> Categories { get; set; } = new();
    public List<Player> RegisteredPlayers { get; set; } = new();
}