using System.Data;
using BLL.Interfaces.Repositories;
using Domain.Entities;
using Microsoft.Data.SqlClient;

namespace DAL.Repositories;

public class CategoryRepository : ICategoryRepository
{
    private readonly string _connectionString;
    
    public CategoryRepository(string connectionString)
    {
        _connectionString = connectionString;
    }

    public async Task<IEnumerable<Category>> GetCategories()
    {
        List<Category> categories = new();
        
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();
        
        using SqlCommand command = new SqlCommand("SELECT * FROM Categories", connection);
        using SqlDataReader reader = await command.ExecuteReaderAsync();
        
        while (await reader.ReadAsync())
        {
            categories.Add(ToEntity(reader));
        }
        
        return categories;
    }

    public async Task<IEnumerable<Category>> GetCategoriesByTournament(int tournamentId)
    {
        List<Category> categories = new();
        
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();
        
        using SqlCommand command = new SqlCommand(
            @"SELECT c.* FROM Categories c 
              JOIN TournamentCategories tc ON c.Id = tc.CategoryId 
              WHERE tc.TournamentId = @TournamentId", connection);
        command.Parameters.AddWithValue("@TournamentId", tournamentId);

        using SqlDataReader reader = await command.ExecuteReaderAsync();
        
        while (await reader.ReadAsync())
        {
            categories.Add(ToEntity(reader));
        }
        
        return categories;
    }

    public async Task AddCategoryToTournament(int tournamentId, int categoryId)
    {
        using SqlConnection connection = new(_connectionString);
        await connection.OpenAsync();

        using SqlCommand command = new(
            "INSERT INTO TournamentCategories (TournamentId, CategoryId) VALUES (@TournamentId, @CategoryId)",
            connection);

        command.Parameters.AddWithValue("@TournamentId", tournamentId);
        command.Parameters.AddWithValue("@CategoryId", categoryId);

        await command.ExecuteNonQueryAsync();
    }

    private static Category ToEntity(SqlDataReader reader) => new()
    {
        Id = reader.GetInt32(reader.GetOrdinal("Id")),
        Name = reader.GetString(reader.GetOrdinal("Name")),
        MinAge = reader.GetInt32(reader.GetOrdinal("MinAge")),
        MaxAge = reader.GetInt32(reader.GetOrdinal("MaxAge"))
    };
}