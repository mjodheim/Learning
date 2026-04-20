using BLL.DTO.Tournament;
using BLL.Interfaces.Repositories;
using BLL.Interfaces.Services;
using BLL.Mappers;
using Domain.Entities;
using Domain.Enums;

namespace BLL.Services;

public class TournamentService : ITournamentService
{
    private readonly ITournamentRepository _tournamentRepository;
    private readonly IInscriptionRepository _inscriptionRepository;
    private readonly IMatchRepository _matchRepository;
    private readonly IMatchService _matchService;

    public TournamentService(
        ITournamentRepository tournamentRepository,
        IInscriptionRepository inscriptionRepository,
        IMatchRepository matchRepository,
        IMatchService matchService)
    {
        _tournamentRepository = tournamentRepository;
        _inscriptionRepository = inscriptionRepository;
        _matchRepository = matchRepository;
        _matchService = matchService;
    }
    
    public async Task<IEnumerable<TournamentReadDto>> GetTournaments()
    {
        IEnumerable<Tournament> tournaments = await _tournamentRepository.GetTournaments();
        return tournaments.Select(TournamentMapper.ToDto);
    }

    public async Task<TournamentReadDto?> GetTournamentById(int id)
    {
        var tournament = await _tournamentRepository.GetTournamentById(id);
        if (tournament == null) return null;
        
        var dto = TournamentMapper.ToDto(tournament);
        
        // Règle n°5 : Inclure les joueurs inscrits
        var players = await _inscriptionRepository.GetPlayersByTournament(id);
        dto.RegisteredPlayers = players.Select(PlayerMapper.ToDto);

        // Bonus Règle n°5 : Inclure les rencontres de la ronde courante
        if (tournament.CurrentRound > 0)
        {
            dto.CurrentRoundMatches = await _matchRepository.GetMatchesByRound(id, tournament.CurrentRound);
        }

        return dto;
    }

    public async Task CreateTournament(TournamentCreateDto tournamentDto)
    {
        if (tournamentDto.MinPlayers > tournamentDto.MaxPlayers && tournamentDto.MaxPlayers > 0)
            throw new InvalidOperationException("Le nombre minimum de joueurs ne peut pas être supérieur au maximum.");

        if (tournamentDto.MinElo > tournamentDto.MaxElo && tournamentDto.MaxElo > 0)
            throw new InvalidOperationException("L'ELO minimum ne peut pas être supérieur à l'ELO maximum.");

        if (tournamentDto.RegistrationDeadline < DateTime.Now.AddDays(tournamentDto.MinPlayers))
            throw new InvalidOperationException($"La date de fin des inscriptions doit être au moins {tournamentDto.MinPlayers} jours après aujourd'hui.");

        Tournament tournament = TournamentMapper.ToEntity(tournamentDto);
        tournament.Status = TournamentStatus.WaitingForPlayers;
        tournament.CurrentRound = 0;
        tournament.CreatedAt = DateTime.Now;
        tournament.UpdatedAt = DateTime.Now;

        await _tournamentRepository.CreateTournament(tournament);
    }

    public async Task UpdateTournament(TournamentUpdateDto tournamentDto)
    {
        throw new NotImplementedException();
    }

    public async Task DeleteTournament(int id)
    {
        var tournament = await _tournamentRepository.GetTournamentById(id);
        if (tournament == null) return;

        if (tournament.Status != TournamentStatus.WaitingForPlayers)
            throw new InvalidOperationException("Seuls les tournois qui n'ont pas commencé peuvent être supprimés.");

        await _tournamentRepository.DeleteTournament(id);
    }

    public async Task StartTournament(int id)
    {
        var tournament = await _tournamentRepository.GetTournamentById(id);
        if (tournament == null) throw new Exception("Tournoi introuvable.");

        var registeredPlayers = await _inscriptionRepository.GetPlayersByTournament(id);
        
        if (registeredPlayers.Count() < tournament.MinPlayers)
            throw new InvalidOperationException("Le nombre minimum de participants n'est pas atteint.");

        if (DateTime.Now < tournament.RegistrationDeadline)
            throw new InvalidOperationException("La date de fin des inscriptions n'est pas encore passée.");

        tournament.Status = TournamentStatus.InProgress;
        tournament.CurrentRound = 1;
        tournament.UpdatedAt = DateTime.Now;

        await _tournamentRepository.UpdateTournament(tournament);
        await _matchService.GenerateRoundRobinMatches(id);
    }
}