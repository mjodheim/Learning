using BLL.DTO.Player;
using BLL.Interfaces.Repositories;
using BLL.Interfaces.Services;
using BLL.Mappers;
using Domain.Entities;

namespace BLL.Services;

public class PlayerService : IPlayerService
{
    private readonly IPlayerRepository _playerRepository;

    public PlayerService(IPlayerRepository playerRepository)
    {
        _playerRepository = playerRepository;
    }
    
    public async Task<IEnumerable<PlayerReadDto>> GetPlayers()
    {
        IEnumerable<Player> players = await _playerRepository.GetPlayers();
        return players.Select(PlayerMapper.ToDto);
    }

    public async Task<PlayerReadDto?> GetPlayerById(int id)
    {
        Player? player = await _playerRepository.GetPlayerById(id);
        if (player is null) return null;
        return PlayerMapper.ToDto(player);
    }

    public async Task CreatePlayer(PlayerCreateDto playerDto)
    {
        Player player = PlayerMapper.ToEntity(playerDto);
        player.PasswordHash = BCrypt.Net.BCrypt.HashPassword(playerDto.Password);
        
        await _playerRepository.CreatePlayer(player);
    }

    public async Task UpdatePlayer(int id, PlayerUpdateDto playerDto)
    {
        Player? player = await _playerRepository.GetPlayerById(id);
        if (player is null) return;

        player.Pseudo = playerDto.Pseudo;
        player.Email = playerDto.Email;
        player.BirthDate = playerDto.BirthDate;
        player.Genre = playerDto.Genre;

        await _playerRepository.UpdatePlayer(player);
    }

    public async Task DeletePlayer(int id)
    {
        await _playerRepository.DeletePlayer(id);
    }
}