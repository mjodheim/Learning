using System.Data;
using BLL.Interfaces.Repositories;
using Domain.Entities;
using Domain.Enums;
using Microsoft.Data.SqlClient;

namespace DAL.Repositories;

public class MatchRepository : IMatchRepository
{
    private readonly string _connectionString;
    
    public MatchRepository(string connectionString)
    {
        _connectionString = connectionString;
    }

    public async Task<IEnumerable<Match>> GetMatchesByTournament(int tournamentId)
    {
        List<Match> matches = new();
        
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();
        
        using SqlCommand command = new SqlCommand("SELECT * FROM Matches WHERE TournamentId = @TournamentId", connection);
        command.Parameters.AddWithValue("@TournamentId", tournamentId);

        using SqlDataReader reader = await command.ExecuteReaderAsync();
        
        while (await reader.ReadAsync())
        {
            matches.Add(ToEntity(reader));
        }
        
        return matches;
    }

    public async Task<IEnumerable<Match>> GetMatchesByRound(int tournamentId, int round)
    {
        List<Match> matches = new();
        
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();
        
        using SqlCommand command = new SqlCommand(
            "SELECT * FROM Matches WHERE TournamentId = @TournamentId AND Round = @Round", 
            connection);
        command.Parameters.AddWithValue("@TournamentId", tournamentId);
        command.Parameters.AddWithValue("@Round", round);

        using SqlDataReader reader = await command.ExecuteReaderAsync();
        
        while (await reader.ReadAsync())
        {
            matches.Add(ToEntity(reader));
        }
        
        return matches;
    }

    public async Task CreateMatches(IEnumerable<Match> matches)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();
        
        using SqlTransaction transaction = connection.BeginTransaction();

        try
        {
            foreach (var match in matches)
            {
                using SqlCommand command = new(
                    @"INSERT INTO Matches (TournamentId, Round, Player1Id, Player2Id, Result) 
                      VALUES (@TournamentId, @Round, @Player1Id, @Player2Id, @Result)",
                    connection, transaction);

                command.Parameters.AddWithValue("@TournamentId", match.TournamentId);
                command.Parameters.AddWithValue("@Round", match.Round);
                command.Parameters.AddWithValue("@Player1Id", match.Player1Id);
                command.Parameters.AddWithValue("@Player2Id", match.Player2Id);
                command.Parameters.AddWithValue("@Result", (int)match.Result);

                await command.ExecuteNonQueryAsync();
            }
            await transaction.CommitAsync();
        }
        catch
        {
            await transaction.RollbackAsync();
            throw;
        }
    }

    public async Task UpdateMatchResult(int matchId, MatchResult result)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new(
            "UPDATE Matches SET Result = @Result WHERE Id = @Id",
            connection);

        command.Parameters.AddWithValue("@Id", matchId);
        command.Parameters.AddWithValue("@Result", (int)result);

        await command.ExecuteNonQueryAsync();
    }

    private static Match ToEntity(SqlDataReader reader) => new()
    {
        Id = reader.GetInt32(reader.GetOrdinal("Id")),
        TournamentId = reader.GetInt32(reader.GetOrdinal("TournamentId")),
        Round = reader.GetInt32(reader.GetOrdinal("Round")),
        Player1Id = reader.GetInt32(reader.GetOrdinal("Player1Id")),
        Player2Id = reader.GetInt32(reader.GetOrdinal("Player2Id")),
        Result = (MatchResult)reader.GetInt32(reader.GetOrdinal("Result"))
    };
}