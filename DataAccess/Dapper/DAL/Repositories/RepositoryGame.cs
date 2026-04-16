using System.Data;
using Microsoft.Data.SqlClient;
using DAL.Models;
using Dapper;
using Dapper.Contrib.Extensions;

namespace DAL.Repositories;

public class RepositoryGame
{
    private readonly string _connectionString;

    public RepositoryGame(string connectionString)
    {
        _connectionString = connectionString;
    }

    private IDbConnection CreateConnection()
    {
        return new SqlConnection();
    }

    #region DAPPER.CONTRIB => CRUD simple approach

    public async Task<int> AddGameWithContribAsync(Game game)
    {
        using IDbConnection connection = CreateConnection();
        return await connection.InsertAsync(game);
    } 
    
    public async Task<Game?> GetGameWithContribAsync(int id)
    {
        using IDbConnection connection = CreateConnection();
        return await connection.GetAsync<Game>(id);
    }
    
    public async Task<IEnumerable<Game>> GetAllTheGamesWithContribAsync()
    {
        using IDbConnection connection = CreateConnection();
        return await connection.GetAllAsync<Game>();
    }
    
    public async Task<bool> ModifyGameWithContribAsync(Game game)
    {
        using IDbConnection connection = CreateConnection();
        return await connection.UpdateAsync(game);
    }
    
    public async Task<bool> DeleteGameWithContribAsync(Game game)
    {
        using IDbConnection connection = CreateConnection();
        return await connection.DeleteAsync(game);
    }

    #endregion

    #region PURE DAPPER => MANUAL SQL

    public async Task<IEnumerable<Game>> GetGamesWithAsyncEditor()
    {
        using IDbConnection connection = CreateConnection();
        const string sql = @"
            SELECT j.*, e.*
            FROM Jeux j
            INNER JOIN Editeurs e ON j.EditeurId = e.Id";
        return await connection.QueryAsync<Game, Editeur, Game>(
            sql,
            (game, editor) => { 
                game.Editor = editor;
                return game;
            },
            splitOn: "Id"
        );
    }

    public async Task<Game?> GetGameWithAsyncDetails(int idGame)
    {
        using IDbConnection connection = CreateConnection();
        const string sql = @"
            SELECT j.*, dj.*
            FROM Jeux j
            LEFT JOIN DetailsJeu dj ON j.Id = dj.JeuId
            WHERE j.Id = @IdJeu
        ";
        IEnumerable<Game> results = await connection.QueryAsync<Game, DetailsJeu, Game>(
            sql,
            (game, details) => { 
                game.Details = details;     // Ont-to-One
                return game;
            },
            new { IdJeu = idGame },         // Secure param
            splitOn: "JeuId"
        );
        return results.FirstOrDefault();
    }

    public async Task<IEnumerable<Game>> GetGamesWithAsyncGenre()
    {
        using IDbConnection connection = CreateConnection();
        const string sql = @"
            SELECT j.*, g.*
            FROM Jeux j
            LEFT JOIN JeuGenres jg ON j.Id = jg.JeuId
            LEFT JOIN Genres g ON g.Id = jg.GenreId
            ORDER BY j.Id
        ";
        Dictionary<int, Game> GameDictionary = new();
        await connection.QueryAsync<Game, Genre, Game>(
            sql,
            (game, genre) =>
            {
                if (!GameDictionary.TryGetValue(game.Id, out Game? myGame))
                    myGame = game;
                if(genre is not null)
                    myGame.Genres.Add((genre));
                return myGame;
            },
            splitOn: "Id"
        );
        return GameDictionary.Values;
    }
    #endregion
}