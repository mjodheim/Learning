package be.mjodheim.motocrudmvc.entities;

import jakarta.persistence.*;

import lombok.*;

@Entity
@NoArgsConstructor // ctor vide
@EqualsAndHashCode @ToString
public class Moto {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false, length = 50)
    private String brand;

    @Getter @Setter
    @Column(nullable = false, length = 50)
    private String model;

    @Getter @Setter
    @Column(nullable = false, length = 50)
    private int cc;

    @Getter @Setter
    private String imageUrl;

    @Getter @Setter
    @Column(length = 500)
    private String description;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "category_id",  nullable = false)
    private Category category;

    public Moto(String brand, String model, int cc, String imageUrl, String description, Category  category) {
        this();
        this.brand = brand;
        this.model = model;
        this.cc = cc;
        this.imageUrl = imageUrl;
        this.description = description;
        this.category = category;
    }
}
