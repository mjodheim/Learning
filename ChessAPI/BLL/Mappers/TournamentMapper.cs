using BLL.DTO.Tournament;
using Domain.Entities;

namespace BLL.Mappers;

public class TournamentMapper
{
    public static Tournament ToDto(Tournament tournament) => new()
    {
        Id = tournament.Id,
        Name = tournament.Name,
        Location = tournament.Location,
        MinPlayers = tournament.MinPlayers,
        MaxPlayers = tournament.MaxPlayers,
        MinElo = tournament.MinElo,
        MaxElo = tournament.MaxElo,
        WomenOnly =  tournament.WomenOnly,
        RegistrationDeadline = tournament.RegistrationDeadline
    };
    
    public static Tournament ToEntity(TournamentCreateDto dto) => new()
    {
        Name = dto.Name,
        Location = dto.Location,
        MinPlayers = dto.MinPlayers,
        MaxPlayers = dto.MaxPlayers,
        MinElo = dto.MinElo,
        MaxElo = dto.MaxElo,
        WomenOnly =  dto.WomenOnly,
        RegistrationDeadline = dto.RegistrationDeadline
    };
}