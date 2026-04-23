using BLL.DTO.Tournament;
using BLL.Interfaces.Services;
using Microsoft.AspNetCore.Mvc;

namespace ChessAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class TournamentController : ControllerBase
{
    private readonly ITournamentService _tournamentService;
    private readonly IMatchService _matchService;

    public TournamentController(ITournamentService tournamentService, IMatchService matchService)
    {
        _tournamentService = tournamentService;
        _matchService = matchService;
    }

    [HttpGet]
    public async Task<IActionResult> GetTournaments()
    {
        var tournaments = await _tournamentService.GetTournaments();
        return Ok(tournaments);
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetTournamentById(int id)
    {
        var tournament = await _tournamentService.GetTournamentById(id);
        if (tournament == null) return NotFound();
        return Ok(tournament);
    }

    [HttpPost]
    public async Task<IActionResult> CreateTournament(TournamentCreateDto dto)
    {
        try
        {
            await _tournamentService.CreateTournament(dto);
            return Created();
        }
        catch (InvalidOperationException ex)
        {
            return BadRequest(ex.Message);
        }
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> UpdateTournament(int id, TournamentUpdateDto dto)
    {
        try
        {
            await _tournamentService.UpdateTournament(id, dto);
            return NoContent();
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

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteTournament(int id)
    {
        try
        {
            await _tournamentService.DeleteTournament(id);
            return NoContent();
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

    [HttpPost("{id}/start")]
    public async Task<IActionResult> StartTournament(int id)
    {
        try
        {
            await _tournamentService.StartTournament(id);
            return Ok("Le tournoi a été démarré.");
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

    [HttpPost("{id}/next-round")]
    public async Task<IActionResult> NextRound(int id)
    {
        try
        {
            await _matchService.NextRound(id);
            return Ok("Passage à la ronde suivante effectué.");
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

    [HttpGet("{id}/scores/{round}")]
    public async Task<IActionResult> GetScores(int id, int round)
    {
        var scores = await _matchService.GetTournamentScores(id, round);
        return Ok(scores);
    }
}
