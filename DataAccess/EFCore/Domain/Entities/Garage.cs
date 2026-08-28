using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace EFCore.Domain.Entities;

[Table("Garages")]
public class Garage
{
    [Key, Column("id")] public int Id { get; set; }
    [Required, Column("name"), MaxLength(50)] public string Name { get; set; } = null!;
    [Required, Column("address"), MaxLength(50)] public string Address { get; set; } = null!;
    public virtual ICollection<Auto> Autos { get; set; } = new List<Auto>();
}