package be.mjodheim.motocrudmvc.mappers;

import be.mjodheim.motocrudmvc.entities.CartLine;
import be.mjodheim.motocrudmvc.models.cart.CartDto;
import be.mjodheim.motocrudmvc.models.cart.CartLineDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {

    public CartDto toDto(List<CartLine> lines) {
        List<CartLineDto> dtos = lines.stream()
                .map(this::toLineDto)
                .toList();

        BigDecimal total = dtos.stream()
                .map(CartLineDto::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int itemCount = dtos.stream()
                .mapToInt(CartLineDto::quantity)
                .sum();

        return new CartDto(dtos, total, itemCount);
    }

    private CartLineDto toLineDto(CartLine line) {
        BigDecimal subtotal = line.getMoto().getPrice()
                .multiply(BigDecimal.valueOf(line.getQuantity()));

        return new CartLineDto(
                line.getMoto().getId(),
                line.getMoto().getBrand(),
                line.getMoto().getModel(),
                line.getMoto().getCc(),
                line.getMoto().getImageUrl(),
                line.getMoto().getPrice(),
                line.getQuantity(),
                subtotal
        );
    }
}
