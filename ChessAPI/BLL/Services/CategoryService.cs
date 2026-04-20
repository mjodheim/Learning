using BLL.Interfaces.Repositories;
using BLL.Interfaces.Services;
using Domain.Entities;

namespace BLL.Services;

public class CategoryService : ICategoryService
{
    private readonly ICategoryRepository _categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository)
    {
        _categoryRepository = categoryRepository;
    }

    public async Task<IEnumerable<Category>> GetCategories()
    {
        return await _categoryRepository.GetCategories();
    }
}
