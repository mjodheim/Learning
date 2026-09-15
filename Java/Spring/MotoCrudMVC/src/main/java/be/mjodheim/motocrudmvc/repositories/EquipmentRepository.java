package be.mjodheim.motocrudmvc.repositories;

import be.mjodheim.motocrudmvc.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
