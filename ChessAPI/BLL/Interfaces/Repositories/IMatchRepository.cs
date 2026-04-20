using Domain.Entities;
using Domain.Enums;

namespace BLL.Interfaces.Repositories;

public interface IMatchRepository
{
    Task<IEnumerable<Match>> GetMatchesByTournament(int tournamentId);
    Task<IEnumerable<Match>> GetMatchesByRound(int tournamentId, int round);
    Task CreateMatches(IEnumerable<Match> matches);
    Task UpdateMatchResult(int matchId, MatchResult result);
}