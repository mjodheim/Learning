package be.mjodheim.motocrudmvc.repositories;

import be.mjodheim.motocrudmvc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
