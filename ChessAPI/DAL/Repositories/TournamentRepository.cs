using BLL.Interfaces.Repositories;
using Domain.Entities;
using Microsoft.Data.SqlClient;

namespace DAL.Repositories;

public class TournamentRepository : ITournamentRepository
{
    private readonly string _connectionString;
    
    public TournamentRepository(string connectionString)
    {
        _connectionString = connectionString;
    }

    public async Task<IEnumerable<Tournament>> GetTournaments()
    {
        List<Tournament> tournaments = new();
        
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();
        
        using SqlCommand command = new SqlCommand("SELECT * FROM Tounaments", connection);
        using SqlDataReader reader = await command.ExecuteReaderAsync();
        
        while (await reader.ReadAsync())
        {
            tournaments.Add(ToEntity(reader));
        }
        
        return tournaments;
    }

    public async Task<Tournament?> GetTournamentById(int id)
    {
        throw new NotImplementedException();
    }

    public async Task CreateTournament(Tournament tournament)
    {
        throw new NotImplementedException();
    }

    public async Task UpdateTournament(Tournament tournament)
    {
        throw new NotImplementedException();
    }

    public async Task DeleteTournament(int id)
    {
        throw new NotImplementedException();
    }
    
    private static Tournament ToEntity(SqlDataReader reader) => new()
    {
        Id = reader.GetInt32("Id"),
        Name = reader.GetString("Name"),
        Location = reader.GetString("Location"),
        MinPlayers = reader.GetInt32("MinPlayers"),
        MaxPlayers = reader.GetInt32("MaxPlayers"),
        MinElo = reader.GetInt32("MinElo"),
        MaxElo = reader.GetInt32("MaxElo"),
        CurrentRound = reader.GetInt32("CurrentRound"),
        WomenOnly = reader.GetInt32("WomenOnly"),
        RegistrationDeadline = reader.GetDateTime("RegistrationDeadline"),
        CreatedAt = reader.GetDateTime("CreatedAt"),
        UpdatedAt = reader.GetDateTime("UpdatedAt")
    };
}