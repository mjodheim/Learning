using System.ComponentModel.DataAnnotations;
using Domain.Enums;

namespace BLL.DTO.Player;

public class PlayerUpdateDto
{
    [Required][StringLength(20, MinimumLength = 3, ErrorMessage = "Player name must be between 3 and 20 characters")]
    public string Pseudo { get; set; } = string.Empty;
    
    [Required][EmailAddress]
    public string Email { get; set; } = string.Empty;
    
    [DataType(DataType.Date)]
    public DateTime BirthDate { get; set; }
    
    public Genre Genre { get; set; }
    
}