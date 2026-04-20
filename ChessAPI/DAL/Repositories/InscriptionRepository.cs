using System.Data;
using BLL.Interfaces.Repositories;
using Domain.Entities;
using Domain.Enums;
using Microsoft.Data.SqlClient;

namespace DAL.Repositories;

public class InscriptionRepository : IInscriptionRepository
{
    private readonly string _connectionString;
    
    public InscriptionRepository(string connectionString)
    {
        _connectionString = connectionString;
    }

    public async Task<IEnumerable<Player>> GetPlayersByTournament(int tournamentId)
    {
        List<Player> players = new();
        
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();
        
        using SqlCommand command = new SqlCommand(
            @"SELECT p.* FROM Players p 
              JOIN Inscriptions i ON p.Id = i.PlayerId 
              WHERE i.TournamentId = @TournamentId", connection);
        command.Parameters.AddWithValue("@TournamentId", tournamentId);

        using SqlDataReader reader = await command.ExecuteReaderAsync();
        
        while (await reader.ReadAsync())
        {
            players.Add(new Player
            {
                Id = reader.GetInt32(reader.GetOrdinal("Id")),
                Pseudo = reader.GetString(reader.GetOrdinal("Pseudo")),
                Email = reader.GetString(reader.GetOrdinal("Email")),
                BirthDate = reader.GetDateTime(reader.GetOrdinal("BirthDate")),
                Genre = (Genre)reader.GetInt32(reader.GetOrdinal("Genre")),
                Elo = reader.GetInt32(reader.GetOrdinal("Elo"))
            });
        }
        
        return players;
    }

    public async Task RegisterPlayer(int tournamentId, int playerId)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new(
            "INSERT INTO Inscriptions (TournamentId, PlayerId, RegisteredAt) VALUES (@TournamentId, @PlayerId, @RegisteredAt)",
            connection);

        command.Parameters.AddWithValue("@TournamentId", tournamentId);
        command.Parameters.AddWithValue("@PlayerId", playerId);
        command.Parameters.AddWithValue("@RegisteredAt", DateTime.Now);

        await command.ExecuteNonQueryAsync();
    }

    public async Task UnregisterPlayer(int tournamentId, int playerId)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new(
            "DELETE FROM Inscriptions WHERE TournamentId = @TournamentId AND PlayerId = @PlayerId",
            connection);

        command.Parameters.AddWithValue("@TournamentId", tournamentId);
        command.Parameters.AddWithValue("@PlayerId", playerId);

        await command.ExecuteNonQueryAsync();
    }
}