using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace EFCore.Domain.Entities;

[Table("Autos")]
[Index(nameof(Plate), IsUnique =  true)]
public class Auto
{
    [Key, Column("id")] public int Id { get; set; }
    [Required, MaxLength(50), Column("brand")] public string Brand { get; set; } = null!;
    [Required, MaxLength(50), Column("model")] public string Model { get; set; } = null!;
    [Required, MaxLength(50), Column("plate")] public string Plate { get; set; } = null!;
    [Required, Column("owner_id")] public int OwnerId { get; set; }
    [ForeignKey(nameof(OwnerId))] public Owner Owner { get; set; } = null!;
    [Required, Column("garage_id")] public int GarageId { get; set; }
    [ForeignKey(nameof(GarageId))] public Garage Garage { get; set; } = null!;
}