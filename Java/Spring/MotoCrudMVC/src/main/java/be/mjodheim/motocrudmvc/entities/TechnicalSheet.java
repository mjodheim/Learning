package be.mjodheim.motocrudmvc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class TechnicalSheet extends BaseEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private Integer horsepower;

    @Getter @Setter
    private Integer weightKg;

    @Getter @Setter
    private Double tankCapacity;

    public TechnicalSheet(Integer horsepower, Integer weightKg, Double tankCapacity) {
        this();
        this.horsepower = horsepower;
        this.weightKg = weightKg;
        this.tankCapacity = tankCapacity;
    }
}
