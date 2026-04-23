using BLL.DTO.Match;
using Domain.Enums;

namespace BLL.Interfaces.Services;

public interface IMatchService
{
    Task GenerateRoundRobinMatches(int tournamentId);
    Task UpdateMatchResult(int matchId, MatchResult result);
    Task NextRound(int tournamentId);
    Task<IEnumerable<ScoreLineDto>> GetTournamentScores(int tournamentId, int round);
}