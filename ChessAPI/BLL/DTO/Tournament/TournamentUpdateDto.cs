using System.ComponentModel.DataAnnotations;

namespace BLL.DTO.Tournament;

public class TournamentUpdateDto
{
    [Required][StringLength(100, MinimumLength = 3,  ErrorMessage = "Name must be between 3 and 100 characters")]
    public string Name { get; set; } = string.Empty;
    
    [Required][StringLength(100, MinimumLength = 3,  ErrorMessage = "Location must be between 3 and 100 characters")]
    public string Location { get; set; } = string.Empty;
    
    
    public int MinPlayers { get; set; }
    public int MaxPlayers { get; set; }
    
    public int MinElo { get; set; }
    public int MaxElo { get; set; }
    
    
    public bool WomenOnly { get; set; }
    
    [DataType(DataType.Date)]
    public DateTime RegistrationDeadline { get; set; }
}