package be.mjodheim.motocrudmvc.controllers;

import be.mjodheim.motocrudmvc.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("cart", cartService.getContent());
        return "cart/index";
    }

    @PostMapping("/add/{motoId}")
    public String add(@PathVariable Long motoId) {
        cartService.add(motoId);
        return "redirect:/cart";
    }

    @PostMapping("/increase/{motoId}")
    public String increase(@PathVariable Long motoId) {
        cartService.add(motoId);
        return "redirect:/cart";
    }

    @PostMapping("/decrease/{motoId}")
    public String decrease(@PathVariable Long motoId) {
        cartService.decrease(motoId);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{motoId}")
    public String remove(@PathVariable Long motoId) {
        cartService.remove(motoId);
        return "redirect:/cart";
    }
}
