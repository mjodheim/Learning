package be.mjodheim.motocrudmvc.models.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartDto(
        List<CartLineDto> lines,
        BigDecimal total,
        int itemCount
) {

    public static CartDto empty() {
        return new CartDto(List.of(), BigDecimal.ZERO, 0);
    }
}
