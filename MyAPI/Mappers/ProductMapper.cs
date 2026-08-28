using MyAPI.Domain.Entities;
using MyAPI.DTO.Product;

namespace MyAPI.Mappers;

public static class ProductMapper
{
    public static ProductResponse ToResponse(Product product)
    {
        return new ProductResponse
        {
            Id = product.Id,
            Name = product.Name,
            Price = product.Price
        };
    }

    public static Product ToEntity(ProductCreateRequest request)
    {
        return new Product
        {
            Name = request.Name,
            Price = request.Price
        };
    }

    public static Product ToEntity(ProductUpdateRequest request, int id)
    {
        return new Product
        {
            Id = id,
            Name = request.Name,
            Price = request.Price
        };
    }
}
