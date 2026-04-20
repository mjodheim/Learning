using Domain.Entities;
using Domain.Enums;

namespace BLL.Interfaces.Services;

public interface IMatchService
{
    Task GenerateRoundRobinMatches(int tournamentId);
    Task UpdateMatchResult(int matchId, MatchResult result);
    Task NextRound(int tournamentId);
    Task<IEnumerable<ScoreLine>> GetTournamentScores(int tournamentId, int round);
}