using BLL.DTO.Match;
using BLL.Interfaces.Services;
using Domain.Enums;
using Microsoft.AspNetCore.Mvc;

namespace ChessAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class MatchController : ControllerBase
{
    private readonly IMatchService _matchService;

    public MatchController(IMatchService matchService)
    {
        _matchService = matchService;
    }
    
    [HttpGet("{matchId}")]
    public async Task<IActionResult> GetMatchById(int matchId)
    {
        MatchReadDto? match = await _matchService.GetMatchById(matchId);
        if (match is null) return NotFound();
        return Ok(match);
    }

    [HttpGet("tournament/{tournamentId}/matches")]
    public async Task<IActionResult> GetMatchsByTournament(int tournamentId)
    {
        IEnumerable<MatchReadDto> matchs = await _matchService.GetMatchesByTournament(tournamentId);
        return Ok(matchs);
    }
    
    [HttpGet("tournament/{tournamentId}/matches/{roundId}")]
    public async Task<IActionResult> GetMatchsByRound(int tournamentId, int roundId)
    {
        IEnumerable<MatchReadDto> matchs = await _matchService.GetMatchesByRound(tournamentId, roundId);
        return Ok(matchs);
    }

    [HttpPut("{id}/result")]
    public async Task<IActionResult> UpdateResult(int id, [FromBody] MatchResult result)
    {
        try
        {
            await _matchService.UpdateMatchResult(id, result);
            return Ok("Résultat mis à jour.");
        }
        catch (KeyNotFoundException ex)
        {
            return NotFound(ex.Message);
        }
        catch (InvalidOperationException ex)
        {
            return BadRequest(ex.Message);
        }
    }
}
