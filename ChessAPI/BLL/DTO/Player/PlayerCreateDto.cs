using System.ComponentModel.DataAnnotations;
using Domain.Enums;

namespace BLL.DTO.Player;

public class PlayerCreateDto
{
    [Required][StringLength(20, MinimumLength = 3, ErrorMessage = "Player name must be between 3 and 20 characters")]
    public string Pseudo { get; set; } = string.Empty;
    
    [Required][EmailAddress]
    public string Email { get; set; } = string.Empty;
    
    [Required][StringLength(100, MinimumLength = 12, ErrorMessage = "Password must be between 12 and 100 characters")]
    public string Password { get; set; } = string.Empty;
    
    [Required][StringLength(100, MinimumLength = 12, ErrorMessage = "Password must be between 12 and 100 characters")]
    [Compare("Password", ErrorMessage = "Passwords do not match")]
    public string ConfirmPassword { get; set; } = string.Empty; 
    
    [DataType(DataType.Date)]
    public DateTime BirthDate { get; set; }
    
    public Genre Genre { get; set; }
    
    [Range(1000, 3500, ErrorMessage = "Elo must be between 800 and 3500")]
    public int Elo { get; set; }
}