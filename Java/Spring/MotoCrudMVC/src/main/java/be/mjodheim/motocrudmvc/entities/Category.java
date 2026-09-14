package be.mjodheim.motocrudmvc.entities;

import jakarta.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode @ToString
public class Category {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false, length = 50)
    private String name;

    public Category(String name) {
        this();
        this.name = name;
    }
}
