package be.mjodheim.motocrudmvc.models.moto;

import be.mjodheim.motocrudmvc.models.category.CategoryDto;

public record MotoIndexDto(
        Long id,
        String brand,
        String model,
        int cc,
        String imageUrl,
        CategoryDto category
) {
}
