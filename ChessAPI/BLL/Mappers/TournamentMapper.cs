using BLL.DTO.Tournament;
using Domain.Entities;

namespace BLL.Mappers;

public class TournamentMapper
{
    public static TournamentReadDto ToDto(Tournament tournament) => new()
    {
        Id = tournament.Id,
        Name = tournament.Name,
        Location = tournament.Location,
        MinPlayers = tournament.MinPlayers,
        MaxPlayers = tournament.MaxPlayers,
        MinElo = tournament.MinElo,
        MaxElo = tournament.MaxElo,
        Status = tournament.Status,
        CurrentRound = tournament.CurrentRound,
        WomenOnly = tournament.WomenOnly,
        RegistrationDeadline = tournament.RegistrationDeadline,
        CreatedAt = tournament.CreatedAt,
        UpdatedAt = tournament.UpdatedAt
    };
    
    public static Tournament ToEntity(TournamentCreateDto dto) => new()
    {
        Name = dto.Name,
        Location = dto.Location,
        MinPlayers = dto.MinPlayers,
        MaxPlayers = dto.MaxPlayers,
        MinElo = dto.MinElo,
        MaxElo = dto.MaxElo,
        WomenOnly = dto.WomenOnly,
        RegistrationDeadline = dto.RegistrationDeadline
    };
}