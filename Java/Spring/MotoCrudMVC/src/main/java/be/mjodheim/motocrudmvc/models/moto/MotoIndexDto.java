package be.mjodheim.motocrudmvc.models.moto;

import be.mjodheim.motocrudmvc.models.category.CategoryDto;

import java.math.BigDecimal;

public record MotoIndexDto(
        Long id,
        String brand,
        String model,
        int cc,
        BigDecimal price,
        String imageUrl,
        CategoryDto category
) {
}
