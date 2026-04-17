using BLL.DTO.Player;
using BLL.Interfaces.Services;
using Microsoft.AspNetCore.Mvc;

namespace ChessAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class PlayerController : ControllerBase
{
    private readonly IPlayerService _playerService;

    public PlayerController(IPlayerService playerService)
    {
        _playerService = playerService;
    }

    [HttpGet]
    public async Task<IActionResult> GetPlayers()
    {
        IEnumerable<PlayerReadDto> players = await _playerService.GetPlayers();
        return Ok(players);
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetPlayerById(int id)
    {
        PlayerReadDto? player = await _playerService.GetPlayerById(id);
        if (player is null) return NotFound();
        return Ok(player);
    }

    [HttpPost]
    public async Task<IActionResult> CreatePlayer(PlayerCreateDto dto)
    {
        await _playerService.CreatePlayer(dto);
        return Created();
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> UpdatePlayer(int id, PlayerUpdateDto dto)
    {
        PlayerReadDto? player = await _playerService.GetPlayerById(id);
        if (player is null) return NotFound();
        await _playerService.UpdatePlayer(id, dto);
        return NoContent();
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeletePlayer(int id)
    {
        PlayerReadDto? player = await _playerService.GetPlayerById(id);
        if (player is null) return NotFound();
        await _playerService.DeletePlayer(id);
        return NoContent();
    }
}