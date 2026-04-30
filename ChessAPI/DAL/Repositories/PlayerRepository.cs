using System.Data;
using BLL.Interfaces.Repositories;
using Domain.Entities;
using Domain.Enums;
using Microsoft.Data.SqlClient;
using Microsoft.Extensions.Configuration;

namespace DAL.Repositories;

public class PlayerRepository : IPlayerRepository
{
    private readonly string _connectionString;
    
    public PlayerRepository(IConfiguration config)
    {
        _connectionString = config.GetConnectionString("DefaultConnection");
    }
    
    public async Task<IEnumerable<Player>> GetPlayers()
    {
        List<Player> players = new();
        
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();
        
        using SqlCommand command = new SqlCommand("SELECT * FROM Players", connection);
        using SqlDataReader reader = await command.ExecuteReaderAsync();
        
        while (await reader.ReadAsync())
        {
            players.Add(ToEntity(reader));
        }
        
        return players;
    }

    public async Task<Player?> GetPlayerById(int id)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new("SELECT * FROM Players WHERE Id = @Id", connection);
        command.Parameters.AddWithValue("@Id", id);

        using SqlDataReader reader = await command.ExecuteReaderAsync();

        if (await reader.ReadAsync())
            return ToEntity(reader);

        return null;
    }

    public async Task<bool> ExistsByPseudo(string pseudo, int? excludeId = null)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        var sql = excludeId.HasValue
            ? "SELECT COUNT(1) FROM Players WHERE Pseudo = @Pseudo AND Id != @ExcludeId"
            : "SELECT COUNT(1) FROM Players WHERE Pseudo = @Pseudo";

        using SqlCommand command = new(sql, connection);
        command.Parameters.AddWithValue("@Pseudo", pseudo);
        if (excludeId.HasValue)
            command.Parameters.AddWithValue("@ExcludeId", excludeId.Value);

        return (int)(await command.ExecuteScalarAsync())! > 0;
    }

    public async Task<bool> ExistsByEmail(string email, int? excludeId = null)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        var sql = excludeId.HasValue
            ? "SELECT COUNT(1) FROM Players WHERE Email = @Email AND Id != @ExcludeId"
            : "SELECT COUNT(1) FROM Players WHERE Email = @Email";

        using SqlCommand command = new(sql, connection);
        command.Parameters.AddWithValue("@Email", email);
        if (excludeId.HasValue)
            command.Parameters.AddWithValue("@ExcludeId", excludeId.Value);

        return (int)(await command.ExecuteScalarAsync())! > 0;
    }

    public async Task CreatePlayer(Player player)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new(
            "INSERT INTO Players (Pseudo, Email, PasswordHash, BirthDate, Genre, Elo) VALUES (@Pseudo, @Email, @PasswordHash, @BirthDate, @Genre, @Elo)",
            connection);

        command.Parameters.AddWithValue("@Pseudo", player.Pseudo);
        command.Parameters.AddWithValue("@Email", player.Email);
        command.Parameters.AddWithValue("@PasswordHash", player.PasswordHash);
        command.Parameters.AddWithValue("@BirthDate", player.BirthDate);
        command.Parameters.AddWithValue("@Genre", player.Genre);
        command.Parameters.AddWithValue("@Elo", player.Elo);

        await command.ExecuteNonQueryAsync();
    }

    public async Task UpdatePlayer(Player player)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new(
            "UPDATE Players SET Pseudo=@Pseudo, Email=@Email, BirthDate=@BirthDate, Genre=@Genre WHERE Id=@Id",
            connection);

        command.Parameters.AddWithValue("@Pseudo", player.Pseudo);
        command.Parameters.AddWithValue("@Email", player.Email);
        command.Parameters.AddWithValue("@BirthDate", player.BirthDate);
        command.Parameters.AddWithValue("@Genre", player.Genre);
        command.Parameters.AddWithValue("@Id", player.Id);

        await command.ExecuteNonQueryAsync();
    }

    public async Task DeletePlayer(int id)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new("DELETE FROM Players WHERE Id = @Id", connection);
        command.Parameters.AddWithValue("@Id", id);

        await command.ExecuteNonQueryAsync();
    }
    
    private static Player ToEntity(SqlDataReader reader) => new()
    {
        Id = reader.GetInt32("Id"),
        Pseudo = reader.GetString("Pseudo"),
        Email = reader.GetString("Email"),
        PasswordHash = reader.GetString("PasswordHash"),
        BirthDate = reader.GetDateTime("BirthDate"),
        Genre = (Genre)reader.GetInt32("Genre"),
        Elo = reader.GetInt32("Elo")
    };
}