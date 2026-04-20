using BLL.Interfaces.Services;
using Microsoft.AspNetCore.Mvc;

namespace ChessAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class InscriptionController : ControllerBase
{
    private readonly IInscriptionService _inscriptionService;

    public InscriptionController(IInscriptionService inscriptionService)
    {
        _inscriptionService = inscriptionService;
    }

    [HttpPost("register")]
    public async Task<IActionResult> Register([FromBody] InscriptionRequest request)
    {
        try
        {
            await _inscriptionService.Register(request.TournamentId, request.PlayerId);
            return Ok("Joueur inscrit avec succès.");
        }
        catch (InvalidOperationException ex)
        {
            return BadRequest(ex.Message);
        }
        catch (Exception ex)
        {
            return StatusCode(500, ex.Message);
        }
    }

    [HttpPost("unregister")]
    public async Task<IActionResult> Unregister([FromBody] InscriptionRequest request)
    {
        try
        {
            await _inscriptionService.Unregister(request.TournamentId, request.PlayerId);
            return Ok("Joueur désinscrit avec succès.");
        }
        catch (InvalidOperationException ex)
        {
            return BadRequest(ex.Message);
        }
    }
}

public record InscriptionRequest(int TournamentId, int PlayerId);
