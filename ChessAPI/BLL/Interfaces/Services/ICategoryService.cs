using BLL.DTO.Category;

namespace BLL.Interfaces.Services;

public interface ICategoryService
{
    Task<IEnumerable<CategoryReadDto>> GetCategories();
    Task<IEnumerable<CategoryReadDto>> GetCategoriesByTournament(int tournamentId);
}