using BLL.DTO.Category;
using Domain.Entities;

namespace BLL.Mappers;

public class CategoryMapper
{
    public static CategoryReadDto ToDto(Category category) => new()
    {
        Id = category.Id,
        Name = category.Name,
        MinAge = category.MinAge,
        MaxAge = category.MaxAge
    };
}