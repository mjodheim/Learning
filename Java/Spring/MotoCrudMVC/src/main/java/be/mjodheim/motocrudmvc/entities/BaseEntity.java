package be.mjodheim.motocrudmvc.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {

    @Getter @Setter
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Getter @Setter
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
