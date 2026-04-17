using Domain.Enums;

namespace BLL.DTO.Player;

public class PlayerCreateDto
{
    public string Pseudo { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
    public string Password { get; set; } = string.Empty;
    public DateTime BirthDate { get; set; }
    public Genre Genre { get; set; }
}