using BLL.DTO.Inscription;
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
    public async Task<IActionResult> Register([FromBody] InscriptionCreateDto request)
    {
        try
        {
            await _inscriptionService.Register(request.TournamentId, request.PlayerId);
            return Ok("Joueur inscrit avec succès.");
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

    [HttpPost("unregister")]
    public async Task<IActionResult> Unregister([FromBody] InscriptionCreateDto request)
    {
        try
        {
            await _inscriptionService.Unregister(request.TournamentId, request.PlayerId);
            return Ok("Joueur désinscrit avec succès.");
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
