package be.mjodheim.motocrudmvc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Equipment extends BaseEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false, unique = true, length = 80)
    private String name;

    @Getter
    @ManyToMany(mappedBy = "equipments", fetch = FetchType.LAZY)
    private Set<Moto> motos = new HashSet<>();

    public Equipment(String name) {
        this();
        this.name = name;
    }
}
