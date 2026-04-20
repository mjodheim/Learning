using Domain.Entities;

namespace BLL.Interfaces.Repositories;

public interface IInscriptionRepository
{
    Task<IEnumerable<Player>> GetPlayersByTournament(int tournamentId);
    Task RegisterPlayer(int tournamentId, int playerId);
    Task UnregisterPlayer(int tournamentId, int playerId);
}