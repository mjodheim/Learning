package be.mjodheim.motocrudmvc.repositories;

import be.mjodheim.motocrudmvc.entities.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartLineRepository extends JpaRepository<CartLine, CartLine.CartLineId> {

    @Query("""
            select cl from CartLine cl
            join fetch cl.moto
            where cl.cart.id = :cartId
            order by cl.moto.brand, cl.moto.model
            """)
    List<CartLine> findByCartId(@Param("cartId") Long cartId);

    Optional<CartLine> findByCart_IdAndMoto_Id(Long cartId, Long motoId);

    @Query("select coalesce(sum(cl.quantity), 0) from CartLine cl where cl.cart.id = :cartId")
    Long countItemsByCartId(@Param("cartId") Long cartId);
}
