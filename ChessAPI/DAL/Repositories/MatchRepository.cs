using BLL.Interfaces.Repositories;

namespace DAL.Repositories;

public class MatchRepository :  IMatchRepository
{
    private readonly string _connectionString;
    
    public MatchRepository(string connectionString)
    {
        _connectionString = connectionString;
    }
}