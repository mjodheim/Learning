using BLL.Interfaces.Repositories;
using BLL.Interfaces.Services;
using Domain.Entities;
using Domain.Enums;

namespace BLL.Services;

public class InscriptionService : IInscriptionService
{
    private readonly IInscriptionRepository _inscriptionRepository;
    private readonly ITournamentRepository _tournamentRepository;
    private readonly IPlayerRepository _playerRepository;
    private readonly ICategoryRepository _categoryRepository;

    public InscriptionService(
        IInscriptionRepository inscriptionRepository,
        ITournamentRepository tournamentRepository,
        IPlayerRepository playerRepository,
        ICategoryRepository categoryRepository)
    {
        _inscriptionRepository = inscriptionRepository;
        _tournamentRepository = tournamentRepository;
        _playerRepository = playerRepository;
        _categoryRepository = categoryRepository;
    }

    public async Task Register(int tournamentId, int playerId)
    {
        var tournament = await _tournamentRepository.GetTournamentById(tournamentId);
        if (tournament == null) throw new Exception("Tournoi introuvable.");

        var player = await _playerRepository.GetPlayerById(playerId);
        if (player == null) throw new Exception("Joueur introuvable.");

        var registeredPlayers = (await _inscriptionRepository.GetPlayersByTournament(tournamentId)).ToList();

        // 1. Le tournoi n’a pas encore commencé
        if (tournament.Status != TournamentStatus.WaitingForPlayers)
            throw new InvalidOperationException("Le tournoi a déjà commencé ou est terminé.");

        // 2. La date de fin des inscriptions n’est pas dépassée
        if (DateTime.Now > tournament.RegistrationDeadline)
            throw new InvalidOperationException("La date limite d'inscription est dépassée.");

        // 3. Le joueur n’est pas déjà inscrit
        if (registeredPlayers.Any(p => p.Id == playerId))
            throw new InvalidOperationException("Le joueur est déjà inscrit à ce tournoi.");

        // 4. Le tournoi n’a pas atteint son nombre maximum
        if (tournament.MaxPlayers > 0 && registeredPlayers.Count >= tournament.MaxPlayers)
            throw new InvalidOperationException("Le tournoi est complet.");

        // 5. L'âge du joueur correspond à une catégorie autorisée
        var categories = await _categoryRepository.GetCategoriesByTournament(tournamentId);
        int ageAtDeadline = CalculateAge(player.BirthDate, tournament.RegistrationDeadline);
        
        bool matchesCategory = !categories.Any() || categories.Any(c => ageAtDeadline >= c.MinAge && (c.MaxAge == 0 || ageAtDeadline <= c.MaxAge));
        if (!matchesCategory)
            throw new InvalidOperationException("L'âge du joueur ne correspond à aucune catégorie du tournoi.");

        // 6. L’ELO du joueur respecte les bornes éventuelles
        if (player.Elo < tournament.MinElo || (tournament.MaxElo > 0 && player.Elo > tournament.MaxElo))
            throw new InvalidOperationException("L'ELO du joueur ne respecte pas les bornes du tournoi.");

        // 7. Le genre du joueur respecte la contrainte WomenOnly
        if (tournament.WomenOnly && player.Genre != Genre.Female)
            throw new InvalidOperationException("Ce tournoi est réservé aux femmes.");

        await _inscriptionRepository.RegisterPlayer(tournamentId, playerId);
    }

    public async Task Unregister(int tournamentId, int playerId)
    {
        var tournament = await _tournamentRepository.GetTournamentById(tournamentId);
        if (tournament == null) throw new Exception("Tournoi introuvable.");

        // Seuls les tournois qui n’ont pas commencé permettent la désinscription
        if (tournament.Status != TournamentStatus.WaitingForPlayers)
            throw new InvalidOperationException("Impossible de se désinscrire : le tournoi a déjà commencé.");

        await _inscriptionRepository.UnregisterPlayer(tournamentId, playerId);
    }

    private int CalculateAge(DateTime birthDate, DateTime referenceDate)
    {
        int age = referenceDate.Year - birthDate.Year;
        if (birthDate.Date > referenceDate.AddYears(-age)) age--;
        return age;
    }
}
