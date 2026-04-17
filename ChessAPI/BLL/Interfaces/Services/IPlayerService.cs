using BLL.DTO.Player;

namespace BLL.Interfaces.Services;

public interface IPlayerService
{
    Task<IEnumerable<PlayerReadDto>> GetPlayers();
    Task<PlayerReadDto?> GetPlayerById(int id);
    Task CreatePlayer(PlayerCreateDto playerDto);
    Task UpdatePlayer(int id, PlayerUpdateDto playerDto);
    Task DeletePlayer(int id);
}