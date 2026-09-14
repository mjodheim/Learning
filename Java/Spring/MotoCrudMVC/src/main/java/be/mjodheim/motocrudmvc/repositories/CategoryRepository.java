package be.mjodheim.motocrudmvc.repositories;

import be.mjodheim.motocrudmvc.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
