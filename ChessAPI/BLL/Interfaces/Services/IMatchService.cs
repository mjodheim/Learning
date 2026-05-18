using BLL.DTO.Match;
using Domain.Enums;

namespace BLL.Interfaces.Services;

public interface IMatchService
{
    Task<MatchReadDto?> GetMatchById(int matchId);
    Task<IEnumerable<MatchReadDto>> GetMatchesByTournament(int tournamentId);
    Task<IEnumerable<MatchReadDto>> GetMatchesByRound(int tournamentId, int round);
    Task GenerateRoundRobinMatches(int tournamentId);
    Task UpdateMatchResult(int matchId, MatchResult result);
    Task NextRound(int tournamentId);
    Task<IEnumerable<ScoreLineDto>> GetTournamentScores(int tournamentId, int round);
}