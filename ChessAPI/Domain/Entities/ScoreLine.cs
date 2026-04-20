namespace Domain.Entities;

public class ScoreLine
{
    public string PlayerName { get; set; } = string.Empty;
    public int MatchesPlayed { get; set; }
    public int Victories { get; set; }
    public int Defeats { get; set; }
    public int Draws { get; set; }
    public double Score { get; set; }
}