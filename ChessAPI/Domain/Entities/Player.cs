using Domain.Enums;

namespace Domain.Entities;

public class Player
{
    public int Id { get; set; }
    public string Pseudo { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
    public string PasswordHash { get; set; } = string.Empty;
    public DateTime BirthDate { get; set; }
    public Genre Genre { get; set; }
    public int Elo { get; set; } = 1200;
}