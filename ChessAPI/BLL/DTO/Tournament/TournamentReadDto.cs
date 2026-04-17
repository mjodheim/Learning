using Domain.Enums;

namespace BLL.DTO.Tournament;

public class TournamentReadDto
{
    public string Name { get; set; } = string.Empty;
    public string Location { get; set; } = string.Empty;
    public int MinPlayers { get; set; }
    public int MaxPlayers { get; set; } 
    public int MinElo { get; set; }
    public int MaxElo { get; set; }
    public TournamentStatus Status { get; set; }
    public int CurrentRound { get; set; }
    public bool WomenOnly { get; set; }
    public DateTime RegistrationDeadline { get; set; }
    public DateTime CreatedAt { get; set; } 
    public DateTime UpdatedAt { get; set; } 
}