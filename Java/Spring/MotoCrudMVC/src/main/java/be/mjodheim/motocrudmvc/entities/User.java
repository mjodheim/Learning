package be.mjodheim.motocrudmvc.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
public class User extends BaseEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Getter @Setter
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Cart cart;

    public User(String username) {
        this();
        this.username = username;
    }
}
