package be.mjodheim.motocrudmvc.models.cart;

import java.math.BigDecimal;

public record CartLineDto(
        Long motoId,
        String brand,
        String model,
        int cc,
        String imageUrl,
        BigDecimal unitPrice,
        int quantity,
        BigDecimal subtotal
) {
}
