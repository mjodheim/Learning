using Domain.Enums;

namespace BLL.DTO.Match;

public class MatchReadDto
{
    public int Id { get; set; }
    public int TournamentId { get; set; }
    public int Round { get; set; }
    public int Player1Id { get; set; }
    public int Player2Id { get; set; }
    public MatchResult Result { get; set; }
}
