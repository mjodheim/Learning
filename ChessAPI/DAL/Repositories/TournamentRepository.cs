using BLL.Interfaces.Repositories;

namespace DAL.Repositories;

public class TournamentRepository : ITournamentRepository
{
    private readonly string _connectionString;
    
    public TournamentRepository(string connectionString)
    {
        _connectionString = connectionString;
    }
}