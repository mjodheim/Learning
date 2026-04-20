using Domain.Enums;

namespace Domain.Entities;

public class Match
{
    public int Id { get; set; }
    public int TournamentId { get; set; }
    public int Round { get; set; }
    public int Player1Id { get; set; }
    public int Player2Id { get; set; }
    public MatchResult Result { get; set; } = MatchResult.Pending;
    
    // Propriétés de navigation optionnelles
    public Player? Player1 { get; set; }
    public Player? Player2 { get; set; }
}