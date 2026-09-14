package be.mjodheim.motocrudmvc.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String name;
}
