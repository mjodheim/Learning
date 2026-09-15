package be.mjodheim.motocrudmvc.services;

import be.mjodheim.motocrudmvc.entities.Cart;
import be.mjodheim.motocrudmvc.entities.CartLine;
import be.mjodheim.motocrudmvc.entities.Moto;
import be.mjodheim.motocrudmvc.entities.User;
import be.mjodheim.motocrudmvc.mappers.CartMapper;
import be.mjodheim.motocrudmvc.models.cart.CartDto;
import be.mjodheim.motocrudmvc.repositories.CartLineRepository;
import be.mjodheim.motocrudmvc.repositories.CartRepository;
import be.mjodheim.motocrudmvc.repositories.MotoRepository;
import be.mjodheim.motocrudmvc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private static final Long CURRENT_USER_ID = 1L;

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartLineRepository cartLineRepository;
    private final MotoRepository motoRepository;
    private final CartMapper cartMapper;

    public void add(Long motoId) {
        Moto moto = motoRepository.findById(motoId).orElseThrow();
        Cart cart = getOrCreateCart();

        Optional<CartLine> existingLine = cartLineRepository.findByCart_IdAndMoto_Id(cart.getId(), motoId);

        if (existingLine.isPresent()) {
            CartLine line = existingLine.get();
            line.setQuantity(line.getQuantity() + 1);
            cartLineRepository.save(line);
            return;
        }

        cartLineRepository.save(new CartLine(1, cart, moto));
    }

    public void decrease(Long motoId) {
        Cart cart = getCart();
        CartLine line = cartLineRepository.findByCart_IdAndMoto_Id(cart.getId(), motoId)
                .orElseThrow();

        if (line.getQuantity() <= 1) {
            cartLineRepository.delete(line);
            return;
        }

        line.setQuantity(line.getQuantity() - 1);
        cartLineRepository.save(line);
    }

    public void remove(Long motoId) {
        Cart cart = getCart();
        CartLine line = cartLineRepository.findByCart_IdAndMoto_Id(cart.getId(), motoId)
                .orElseThrow();

        cartLineRepository.delete(line);
    }

    @Transactional(readOnly = true)
    public CartDto getContent() {
        Optional<Cart> cart = cartRepository.findByUserId(CURRENT_USER_ID);

        if (cart.isEmpty()) {
            return CartDto.empty();
        }

        List<CartLine> lines = cartLineRepository.findByCartId(cart.get().getId());
        return cartMapper.toDto(lines);
    }

    @Transactional(readOnly = true)
    public int getItemCount() {
        Optional<Cart> cart = cartRepository.findByUserId(CURRENT_USER_ID);

        if (cart.isEmpty()) {
            return 0;
        }

        return cartLineRepository.countItemsByCartId(cart.get().getId()).intValue();
    }

    private Cart getOrCreateCart() {
        Optional<Cart> cart = cartRepository.findByUserId(CURRENT_USER_ID);

        if (cart.isPresent()) {
            return cart.get();
        }

        User user = userRepository.findById(CURRENT_USER_ID).orElseThrow();
        return cartRepository.save(new Cart(user));
    }

    private Cart getCart() {
        return cartRepository.findByUserId(CURRENT_USER_ID).orElseThrow();
    }
}
