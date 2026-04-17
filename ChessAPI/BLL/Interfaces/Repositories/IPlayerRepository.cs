using Domain.Entities;

namespace BLL.Interfaces.Repositories;

public interface IPlayerRepository
{
    Task<IEnumerable<Player>> GetPlayers();
    Task<Player?> GetPlayerById(int id);
    Task CreatePlayer(Player player);
    Task UpdatePlayer(Player player);
    Task DeletePlayer(int id);
}