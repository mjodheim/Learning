package be.mjodheim.motocrudmvc.models.moto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class MotoForm {

    @NotBlank
    @Size(max = 50)
    private String brand;

    @NotBlank
    @Size(max = 50)
    private String model;

    @NotNull
    @Min(50)
    @Max(2500)
    private Integer cc;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @Size(max = 255)
    private String imageUrl;

    @Size(max = 500)
    private String description;

    @NotNull
    @Min(1)
    private Long categoryId;

    @Min(1)
    private Integer horsepower;

    @Min(1)
    private Integer weightKg;

    @DecimalMin("1.0")
    private Double tankCapacity;

    private Set<Long> equipmentIds = new HashSet<>();
}
