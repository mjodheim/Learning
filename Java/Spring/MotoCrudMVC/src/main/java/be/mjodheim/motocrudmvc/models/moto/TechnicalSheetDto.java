package be.mjodheim.motocrudmvc.models.moto;

public record TechnicalSheetDto(
        Integer horsepower,
        Integer weightKg,
        Double tankCapacity
) {
}
