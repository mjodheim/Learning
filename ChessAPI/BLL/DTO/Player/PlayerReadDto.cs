using Domain.Enums;

namespace BLL.DTO.Player;

public class PlayerReadDto
{
    public int Id { get; set; }
    public string Pseudo { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
    public DateTime BirthDate { get; set; }
    public Genre Genre { get; set; }
    public int Elo { get; set; }
}