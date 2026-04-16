using System.ComponentModel.DataAnnotations;

namespace MyAPI.DTO.Product;

public class ProductCreateRequest
{
    [Required]
    [StringLength(100)]
    public string Name { get; set; } = "";

    [Required]
    [Range(0.01, 5000)]
    public decimal Price { get; set; }
}
