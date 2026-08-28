using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace EFCore.Domain.Entities;

[Table("Owners")]
public class Owner
{
    [Key, Required, Column("id")] public int Id { get; set; }
    [Required, Column("name"), MaxLength(50)] public string Name { get; set; } = null!;
    [Required, Column("email"), MaxLength(50)] public string Email { get; set; } = null!;
    public virtual ICollection<Auto> Autos { get; set; } = new List<Auto>();
}