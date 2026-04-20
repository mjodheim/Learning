using Domain.Entities;

namespace BLL.Interfaces.Repositories;

public interface ICategoryRepository
{
    Task<IEnumerable<Category>> GetCategories();
    Task<IEnumerable<Category>> GetCategoriesByTournament(int tournamentId);
    Task AddCategoryToTournament(int tournamentId, int categoryId);
}