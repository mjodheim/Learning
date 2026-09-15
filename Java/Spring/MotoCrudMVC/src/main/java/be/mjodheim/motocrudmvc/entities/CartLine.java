package be.mjodheim.motocrudmvc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NoArgsConstructor
public class CartLine extends BaseEntity {

    @Getter
    @EmbeddedId
    private CartLineId id = new CartLineId();

    @Getter @Setter
    @Column(nullable = false)
    private int quantity;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    @MapsId("cartId")
    private Cart cart;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moto_id", nullable = false)
    @MapsId("motoId")
    private Moto moto;

    public CartLine(int quantity, Cart cart, Moto moto) {
        this();
        this.quantity = quantity;
        setCart(cart);
        setMoto(moto);
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        this.id.setCartId(cart.getId());
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
        this.id.setMotoId(moto.getId());
    }

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class CartLineId implements Serializable {

        @Getter @Setter
        private Long cartId;

        @Getter @Setter
        private Long motoId;
    }
}
