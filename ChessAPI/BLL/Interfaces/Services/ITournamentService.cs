using BLL.DTO.Tournament;

namespace BLL.Interfaces.Services;

public interface ITournamentService
{
    Task<IEnumerable<TournamentReadDto>> GetTournaments();
    Task<TournamentReadDto?> GetTournamentById(int id);
    Task CreateTournament(TournamentCreateDto tournament);
    Task UpdateTournament(int id, TournamentUpdateDto tournament);
    Task DeleteTournament(int id);
    Task StartTournament(int id);
}