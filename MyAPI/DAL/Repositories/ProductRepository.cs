using Microsoft.Data.SqlClient;
using Microsoft.Extensions.Configuration;
using MyAPI.DAL.Interfaces;
using MyAPI.Domain.Entities;

namespace MyAPI.DAL.Repositories;

public class ProductRepository : IProductRepository
{
    private readonly string _connectionString;

    public ProductRepository(IConfiguration configuration)
    {
        _connectionString = configuration.GetConnectionString("DefaultConnection")!;
    }

    public async Task<List<Product>> GetAllAsync(int page, int pageSize, decimal? minPrice, decimal? maxPrice, string? name)
    {
        var products = new List<Product>();

        await using SqlConnection connection = new SqlConnection(_connectionString);

        var query = "SELECT Id, Name, Price FROM Products WHERE 1=1";

        await using SqlCommand command = new SqlCommand();
        command.Connection = connection;

        if (minPrice.HasValue)
        {
            query += " AND Price >= @MinPrice";
            command.Parameters.AddWithValue("@MinPrice", minPrice.Value);
        }

        if (maxPrice.HasValue)
        {
            query += " AND Price <= @MaxPrice";
            command.Parameters.AddWithValue("@MaxPrice", maxPrice.Value);
        }

        if (!string.IsNullOrWhiteSpace(name))
        {
            query += " AND Name LIKE @Name";
            command.Parameters.AddWithValue("@Name", $"%{name}%");
        }

        query += @"
            ORDER BY Id DESC
            OFFSET @Offset ROWS
            FETCH NEXT @PageSize ROWS ONLY";

        int offset = (page - 1) * pageSize;
        command.Parameters.AddWithValue("@Offset", offset);
        command.Parameters.AddWithValue("@PageSize", pageSize);

        command.CommandText = query;

        await connection.OpenAsync();
        await using SqlDataReader reader = await command.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            products.Add(new Product
            {
                Id = Convert.ToInt32(reader["Id"]),
                Name = reader["Name"].ToString() ?? "",
                Price = Convert.ToDecimal(reader["Price"])
            });
        }

        return products;
    }

    public async Task<Product?> GetByIdAsync(int id)
    {
        await using SqlConnection connection = new SqlConnection(_connectionString);
        const string query = "SELECT Id, Name, Price FROM Products WHERE Id = @Id";

        await using SqlCommand command = new SqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", id);

        await connection.OpenAsync();
        await using SqlDataReader reader = await command.ExecuteReaderAsync();

        if (await reader.ReadAsync())
        {
            return new Product
            {
                Id = Convert.ToInt32(reader["Id"]),
                Name = reader["Name"].ToString() ?? "",
                Price = Convert.ToDecimal(reader["Price"])
            };
        }

        return null;
    }

    public async Task<int> AddAsync(Product product)
    {
        await using SqlConnection connection = new SqlConnection(_connectionString);
        const string query = "INSERT INTO Products (Name, Price) OUTPUT INSERTED.Id VALUES (@Name, @Price)";

        await using SqlCommand command = new SqlCommand(query, connection);
        command.Parameters.AddWithValue("@Name", product.Name);
        command.Parameters.AddWithValue("@Price", product.Price);

        await connection.OpenAsync();
        return Convert.ToInt32(await command.ExecuteScalarAsync());
    }

    public async Task<bool> UpdateAsync(Product product)
    {
        await using SqlConnection connection = new SqlConnection(_connectionString);
        const string query = "UPDATE Products SET Name = @Name, Price = @Price WHERE Id = @Id";

        await using SqlCommand command = new SqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", product.Id);
        command.Parameters.AddWithValue("@Name", product.Name);
        command.Parameters.AddWithValue("@Price", product.Price);

        await connection.OpenAsync();
        int rowsAffected = await command.ExecuteNonQueryAsync();

        return rowsAffected > 0;
    }

    public async Task<bool> DeleteAsync(int id)
    {
        await using SqlConnection connection = new SqlConnection(_connectionString);
        const string query = "DELETE FROM Products WHERE Id = @Id";

        await using SqlCommand command = new SqlCommand(query, connection);
        command.Parameters.AddWithValue("@Id", id);

        await connection.OpenAsync();
        int rowsAffected = await command.ExecuteNonQueryAsync();

        return rowsAffected > 0;
    }
}
