using BLL.DTO.Category;
using BLL.DTO.Match;
using BLL.DTO.Player;
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

    public IEnumerable<CategoryReadDto> Categories { get; set; } = new List<CategoryReadDto>();
    public IEnumerable<PlayerReadDto> RegisteredPlayers { get; set; } = new List<PlayerReadDto>();
    public IEnumerable<MatchReadDto> CurrentRoundMatches { get; set; } = new List<MatchReadDto>();
}