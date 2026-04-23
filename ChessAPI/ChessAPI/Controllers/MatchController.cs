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
