using BLL.DTO.Match;
using Domain.Entities;

namespace BLL.Mappers;

public class MatchMapper
{
    public static MatchReadDto ToDto(Match match) => new()
    {
        Id = match.Id,
        TournamentId = match.TournamentId,
        Round = match.Round,
        Player1Id = match.Player1Id,
        Player2Id = match.Player2Id,
        Result = match.Result
    };
}
