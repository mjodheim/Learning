using Domain.Enums;

namespace BLL.DTO.Tournament;

public class TournamentReadDto
{
    public int Id { get; set; }
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

    public IEnumerable<BLL.DTO.Player.PlayerReadDto> RegisteredPlayers { get; set; } = new List<BLL.DTO.Player.PlayerReadDto>();
    public IEnumerable<Domain.Entities.Match> CurrentRoundMatches { get; set; } = new List<Domain.Entities.Match>();
}