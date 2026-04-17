using BLL.DTO.Tournament;
using BLL.Interfaces.Repositories;
using BLL.Interfaces.Services;
using BLL.Mappers;
using Domain.Entities;

namespace BLL.Services;

public class TournamentService : ITournamentService
{
    private readonly ITournamentRepository _tournamentRepository;

    public TournamentService(ITournamentRepository tournamentRepository)
    {
        _tournamentRepository = tournamentRepository;
    }
    
    public async Task<IEnumerable<TournamentReadDto>> GetTournaments()
    {
        IEnumerable<Tournament> tournaments = await _tournamentRepository.GetTournaments();
        return tournaments.Select(TournamentMapper.ToDto);
    }

    public async Task<TournamentReadDto?> GetTournamentById(int id)
    {
        throw new NotImplementedException();
    }

    public async Task CreateTournament(TournamentCreateDto tournament)
    {
        throw new NotImplementedException();
    }

    public async Task UpdateTournament(TournamentUpdateDto tournament)
    {
        throw new NotImplementedException();
    }

    public async Task DeleteTournament(int id)
    {
        throw new NotImplementedException();
    }
}