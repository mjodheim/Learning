using BLL.DTO.Player;
using Domain.Entities;

namespace BLL.Mappers;

public class PlayerMapper
{
    public static PlayerReadDto ToDto(Player player) => new()
    {
        Id = player.Id,
        Pseudo = player.Pseudo,
        Email = player.Email,
        BirthDate = player.BirthDate,
        Genre = player.Genre,
        Elo = player.Elo
    };

    public static Player ToEntity(PlayerCreateDto dto) => new()
    {
        Pseudo = dto.Pseudo,
        Email = dto.Email,
        BirthDate = dto.BirthDate,
        Genre = dto.Genre,
        Elo = 1200
    };
}