package be.mjodheim.introspringmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/say-hello/{name}")
    public String hello(
            @PathVariable String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/say-bye")
    public String bye(
            @RequestParam String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "bye";
    }
}
