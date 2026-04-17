using BLL.Interfaces.Repositories;

namespace DAL.Repositories;

public class CategoryRepository : ICategoryRepository
{
    private readonly string _connectionString;
    
    public CategoryRepository(string connectionString)
    {
        _connectionString = connectionString;
    }
}