using System.ComponentModel.DataAnnotations;

namespace BLL.DTO.Tournament;

public class TournamentCreateDto
{
    [Required][StringLength(100, MinimumLength = 3,  ErrorMessage = "Name must be between 3 and 100 characters")]
    public string Name { get; set; } = string.Empty;
    
    [Required][StringLength(100, MinimumLength = 3,  ErrorMessage = "Location must be between 3 and 100 characters")]
    public string Location { get; set; } = string.Empty;
    
    
    public int MinPlayers { get; set; }
    public int MaxPlayers { get; set; }
    
    [Range(1000, 3500, ErrorMessage = "Elo must be between 1000 and 3500")]
    public int MinElo { get; set; }
    
    [Range(1000, 3500, ErrorMessage = "Elo must be between 1000 and 3500")]
    public int MaxElo { get; set; }
    
    
    public bool WomenOnly { get; set; }
    
    [DataType(DataType.Date)]
    public DateTime RegistrationDeadline { get; set; }
}