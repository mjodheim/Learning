using Domain.Entities;

namespace BLL.Interfaces.Repositories;

public interface IPlayerRepository
{
    Task<IEnumerable<Player>> GetPlayers();
    Task<Player?> GetPlayerById(int id);
    Task<bool> ExistsByPseudo(string pseudo, int? excludeId = null);
    Task<bool> ExistsByEmail(string email, int? excludeId = null);
    Task CreatePlayer(Player player);
    Task UpdatePlayer(Player player);
    Task DeletePlayer(int id);
}