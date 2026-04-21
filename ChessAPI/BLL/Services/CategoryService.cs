using BLL.DTO.Category;
using BLL.Interfaces;
using BLL.Interfaces.Repositories;
using BLL.Mappers;
using Domain.Entities;

namespace BLL.Services;

public class CategoryService : ICategoryService
{
    private readonly ICategoryRepository _categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository)
    {
        _categoryRepository = categoryRepository;
    }

    public async Task<IEnumerable<CategoryReadDto>> GetCategories()
    {
         IEnumerable<Category> categories = await _categoryRepository.GetCategories();
         return categories.Select(CategoryMapper.ToDto);
    }

    public async Task<IEnumerable<CategoryReadDto>> GetCategoriesByTournament(int tournamentId)
    {
        IEnumerable<Category> categories = await _categoryRepository.GetCategoriesByTournament(tournamentId);
        return categories.Select(CategoryMapper.ToDto);
    }
}
