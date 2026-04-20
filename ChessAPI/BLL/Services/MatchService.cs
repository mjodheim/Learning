using BLL.Interfaces.Repositories;
using BLL.Interfaces.Services;
using Domain.Entities;
using Domain.Enums;

namespace BLL.Services;

public class MatchService : IMatchService
{
    private readonly IMatchRepository _matchRepository;
    private readonly ITournamentRepository _tournamentRepository;
    private readonly IInscriptionRepository _inscriptionRepository;

    public MatchService(
        IMatchRepository matchRepository,
        ITournamentRepository tournamentRepository,
        IInscriptionRepository inscriptionRepository)
    {
        _matchRepository = matchRepository;
        _tournamentRepository = tournamentRepository;
        _inscriptionRepository = inscriptionRepository;
    }

    public async Task GenerateRoundRobinMatches(int tournamentId)
    {
        var players = (await _inscriptionRepository.GetPlayersByTournament(tournamentId)).ToList();
        if (players.Count < 2) throw new InvalidOperationException("Pas assez de joueurs pour générer des rencontres.");

        bool odd = players.Count % 2 != 0;
        if (odd) players.Add(new Player { Id = -1 }); // Dummy player for bye

        int n = players.Count;
        int roundsPerCycle = n - 1;
        List<Match> matches = new();

        // Cycle 1: Aller
        for (int r = 0; r < roundsPerCycle; r++)
        {
            for (int i = 0; i < n / 2; i++)
            {
                var p1 = players[i];
                var p2 = players[n - 1 - i];

                if (p1.Id != -1 && p2.Id != -1)
                {
                    matches.Add(new Match { TournamentId = tournamentId, Round = r + 1, Player1Id = p1.Id, Player2Id = p2.Id });
                }
            }
            RotatePlayers(players);
        }

        // Cycle 2: Retour
        // Reset players rotation or continue? Let's just swap colors and add to subsequent rounds
        for (int r = 0; r < roundsPerCycle; r++)
        {
            for (int i = 0; i < n / 2; i++)
            {
                var p1 = players[i];
                var p2 = players[n - 1 - i];

                if (p1.Id != -1 && p2.Id != -1)
                {
                    matches.Add(new Match { TournamentId = tournamentId, Round = r + 1 + roundsPerCycle, Player1Id = p2.Id, Player2Id = p1.Id });
                }
            }
            RotatePlayers(players);
        }

        await _matchRepository.CreateMatches(matches);
    }

    private void RotatePlayers(List<Player> players)
    {
        var last = players[players.Count - 1];
        players.RemoveAt(players.Count - 1);
        players.Insert(1, last);
    }

    public async Task UpdateMatchResult(int matchId, MatchResult result)
    {
        // On pourrait ajouter des validations ici (ex: vérifier que c'est la ronde courante)
        await _matchRepository.UpdateMatchResult(matchId, result);
    }

    public async Task NextRound(int tournamentId)
    {
        var tournament = await _tournamentRepository.GetTournamentById(tournamentId);
        if (tournament == null) throw new Exception("Tournoi introuvable.");

        var matches = await _matchRepository.GetMatchesByRound(tournamentId, tournament.CurrentRound);
        if (matches.Any(m => m.Result == MatchResult.Pending))
            throw new InvalidOperationException("Tous les matchs de la ronde courante ne sont pas terminés.");

        tournament.CurrentRound++;
        tournament.UpdatedAt = DateTime.Now;
        await _tournamentRepository.UpdateTournament(tournament);
    }

    public async Task<IEnumerable<ScoreLine>> GetTournamentScores(int tournamentId, int round)
    {
        var allMatches = (await _matchRepository.GetMatchesByTournament(tournamentId))
            .Where(m => m.Round <= round && m.Result != MatchResult.Pending);
        
        var players = await _inscriptionRepository.GetPlayersByTournament(tournamentId);
        var scores = new List<ScoreLine>();

        foreach (var player in players)
        {
            var pMatches = allMatches.Where(m => m.Player1Id == player.Id || m.Player2Id == player.Id).ToList();
            
            int wins = pMatches.Count(m => (m.Player1Id == player.Id && m.Result == MatchResult.WhiteWin) || 
                                           (m.Player2Id == player.Id && m.Result == MatchResult.BlackWin));
            int draws = pMatches.Count(m => m.Result == MatchResult.Draw);
            int losses = pMatches.Count - wins - draws;

            scores.Add(new ScoreLine
            {
                PlayerName = player.Pseudo,
                MatchesPlayed = pMatches.Count,
                Victories = wins,
                Draws = draws,
                Defeats = losses,
                Score = wins + (draws * 0.5)
            });
        }

        return scores.OrderByDescending(s => s.Score).ThenByDescending(s => s.Victories);
    }
}
