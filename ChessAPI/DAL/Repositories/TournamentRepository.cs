using BLL.Interfaces.Repositories;
using Domain.Entities;
using Domain.Enums;
using Microsoft.Data.SqlClient;
using System.Data;

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
        
        using SqlCommand command = new SqlCommand("SELECT TOP 10 * FROM Tournaments WHERE Status != 3 ORDER BY UpdatedAt DESC", connection);
        using SqlDataReader reader = await command.ExecuteReaderAsync();
        
        while (await reader.ReadAsync())
        {
            tournaments.Add(ToEntity(reader));
        }
        
        return tournaments;
    }

    public async Task<Tournament?> GetTournamentById(int id)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new("SELECT * FROM Tournaments WHERE Id = @Id", connection);
        command.Parameters.AddWithValue("@Id", id);

        using SqlDataReader reader = await command.ExecuteReaderAsync();

        if (await reader.ReadAsync())
            return ToEntity(reader);

        return null;
    }

    public async Task<int> CreateTournament(Tournament tournament)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new(
            @"INSERT INTO Tournaments (Name, Location, MinPlayers, MaxPlayers, MinElo, MaxElo, Status, CurrentRound, WomenOnly, RegistrationDeadline, CreatedAt, UpdatedAt)
              VALUES (@Name, @Location, @MinPlayers, @MaxPlayers, @MinElo, @MaxElo, @Status, @CurrentRound, @WomenOnly, @RegistrationDeadline, @CreatedAt, @UpdatedAt);
              SELECT CAST(SCOPE_IDENTITY() AS int);",
            connection);

        AddParameters(command, tournament);

        var result = await command.ExecuteScalarAsync();
        return result is int id ? id : Convert.ToInt32(result);
    }

    public async Task UpdateTournament(Tournament tournament)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new(
            @"UPDATE Tournaments 
              SET Name=@Name, Location=@Location, MinPlayers=@MinPlayers, MaxPlayers=@MaxPlayers, 
                  MinElo=@MinElo, MaxElo=@MaxElo, Status=@Status, CurrentRound=@CurrentRound, 
                  WomenOnly=@WomenOnly, RegistrationDeadline=@RegistrationDeadline, UpdatedAt=@UpdatedAt 
              WHERE Id=@Id",
            connection);

        command.Parameters.AddWithValue("@Id", tournament.Id);
        AddParameters(command, tournament);

        await command.ExecuteNonQueryAsync();
    }

    public async Task DeleteTournament(int id)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new("DELETE FROM Tournaments WHERE Id = @Id", connection);
        command.Parameters.AddWithValue("@Id", id);

        await command.ExecuteNonQueryAsync();
    }

    private static void AddParameters(SqlCommand command, Tournament tournament)
    {
        command.Parameters.AddWithValue("@Name", tournament.Name);
        command.Parameters.AddWithValue("@Location", tournament.Location);
        command.Parameters.AddWithValue("@MinPlayers", tournament.MinPlayers);
        command.Parameters.AddWithValue("@MaxPlayers", tournament.MaxPlayers);
        command.Parameters.AddWithValue("@MinElo", tournament.MinElo);
        command.Parameters.AddWithValue("@MaxElo", (object?)tournament.MaxElo ?? DBNull.Value);
        command.Parameters.AddWithValue("@Status", (int)tournament.Status);
        command.Parameters.AddWithValue("@CurrentRound", tournament.CurrentRound);
        command.Parameters.AddWithValue("@WomenOnly", tournament.WomenOnly);
        command.Parameters.AddWithValue("@RegistrationDeadline", tournament.RegistrationDeadline);
        command.Parameters.AddWithValue("@CreatedAt", tournament.CreatedAt);
        command.Parameters.AddWithValue("@UpdatedAt", tournament.UpdatedAt);
    }
    
    private static Tournament ToEntity(SqlDataReader reader) => new()
    {
        Id = reader.GetInt32(reader.GetOrdinal("Id")),
        Name = reader.GetString(reader.GetOrdinal("Name")),
        Location = reader.GetString(reader.GetOrdinal("Location")),
        MinPlayers = reader.GetInt32(reader.GetOrdinal("MinPlayers")),
        MaxPlayers = reader.GetInt32(reader.GetOrdinal("MaxPlayers")),
        MinElo = reader.IsDBNull(reader.GetOrdinal("MinElo")) ? 0 : reader.GetInt32(reader.GetOrdinal("MinElo")),
        MaxElo = reader.IsDBNull(reader.GetOrdinal("MaxElo")) ? 0 : reader.GetInt32(reader.GetOrdinal("MaxElo")),
        Status = (TournamentStatus)reader.GetInt32(reader.GetOrdinal("Status")),
        CurrentRound = reader.GetInt32(reader.GetOrdinal("CurrentRound")),
        WomenOnly = reader.GetBoolean(reader.GetOrdinal("WomenOnly")),
        RegistrationDeadline = reader.GetDateTime(reader.GetOrdinal("RegistrationDeadline")),
        CreatedAt = reader.GetDateTime(reader.GetOrdinal("CreatedAt")),
        UpdatedAt = reader.GetDateTime(reader.GetOrdinal("UpdatedAt"))
    };
}