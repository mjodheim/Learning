package be.mjodheim.motocrudmvc.repositories;

import be.mjodheim.motocrudmvc.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    @Query("""
            select m from Moto m
            where (:brand is null or :brand = '' or lower(m.brand) = lower(:brand))
            and (:categoryId is null or m.category.id = :categoryId)
            order by m.brand, m.model
            """)
    List<Moto> findWithFilter(
            @Param("brand") String brand,
            @Param("categoryId") Long categoryId
    );

    @Query("select distinct m.brand from Moto m order by m.brand")
    List<String> findAllBrands();
}
