package be.mjodheim.motocrudmvc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor // ctor vide
public class Moto extends BaseEntity {

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
    @Column(nullable = false)
    private int cc;

    @Getter @Setter
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Getter @Setter
    private String imageUrl;

    @Getter @Setter
    @Column(length = 500)
    private String description;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "technical_sheet_id", unique = true)
    private TechnicalSheet technicalSheet;

    @Getter
    @ManyToMany
    @JoinTable(
            name = "moto_equipment",
            joinColumns = @JoinColumn(name = "moto_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();

    public Moto(String brand, String model, int cc, BigDecimal price, String imageUrl, String description, Category category) {
        this();
        this.brand = brand;
        this.model = model;
        this.cc = cc;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.category = category;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments.clear();

        if (equipments != null) {
            this.equipments.addAll(equipments);
        }
    }
}
