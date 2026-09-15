package be.mjodheim.motocrudmvc.models.moto;

import be.mjodheim.motocrudmvc.models.category.CategoryDto;
import be.mjodheim.motocrudmvc.models.equipment.EquipmentDto;

import java.util.List;

public record MotoDetailsDto(
        Long id,
        String brand,
        String model,
        int cc,
        String imageUrl,
        String description,
        CategoryDto category,
        TechnicalSheetDto technicalSheet,
        List<EquipmentDto> equipments
) {
}
