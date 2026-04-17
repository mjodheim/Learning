using BLL.Interfaces.Repositories;

namespace DAL.Repositories;

public class InscriptionRepository :  IInscriptionRepository
{
    private readonly string _connectionString;
    
    public InscriptionRepository(string connectionString)
    {
        _connectionString = connectionString;
    }
}