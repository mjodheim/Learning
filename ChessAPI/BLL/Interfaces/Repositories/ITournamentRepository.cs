using Domain.Entities;

namespace BLL.Interfaces.Repositories;

public interface ITournamentRepository
{
    Task<IEnumerable<Tournament>> GetTournaments();
    Task<Tournament?> GetTournamentById(int id);
    Task<int> CreateTournament(Tournament tournament);
    Task UpdateTournament(Tournament tournament);
    Task DeleteTournament(int id);
}