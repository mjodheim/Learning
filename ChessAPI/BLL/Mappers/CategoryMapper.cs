using BLL.DTO.Category;
using Domain.Entities;

namespace BLL.Mappers;

public class CategoryMapper
{
    public static CategoryReadDto ToDto(Category category) => new()
    {
        Name = category.Name
    };
}