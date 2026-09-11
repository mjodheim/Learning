package be.mjodheim.introspringmvc.controllers;

import be.mjodheim.introspringmvc.models.Moto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    public List<Moto> motos =  List.of(
            new Moto(1L, "Honda", "CBR 600 RR", 600,"https://www.fr.honda.be/content/dam/central/motorcycles/colour-picker/supersports/cbr600rr/cbr600rr_2024/r-380_grandprixred/cbr600rr_2024_r-380_grandprixred.png"),
            new Moto(2L, "Yamaha", "MT-07", 689,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkVki89t0pKni8dFXLjoQPto4BcNsf4kn6VloyK48-Pg&s=10"),
            new Moto(3L, "Kawasaki", "Z900", 948,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiQqcJWNzfNXOeBk8jv4S1d0Nw0VZkyfG1M-Z3MqnRhg&s=10"),
            new Moto(4L, "Ducati", "Monster", 937,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHTkbwkIRFW-RIU07rLhzK2kQ3o9tIpTGbwac00xXnFg&s=10")
    );

    @GetMapping("/")
    public String home(
            @RequestParam (required = false) String brand,
            Model model
    ){
        model.addAttribute("motos",
                (brand == null || brand.isEmpty()) ? motos : motos.stream()
                        .filter(moto -> brand
                                .equalsIgnoreCase(moto.getBrand()))
                        .distinct()
                        .findAny().orElse(null));
        model.addAttribute("brand", brand);
        return "home";
    }

    @GetMapping("/{id}")
    public String moto(
        @PathVariable int id,
        Model model
    ){
        model.addAttribute("moto", motos.stream()
                .filter(moto -> moto.getId() == id).findFirst().orElse(null));
        return "moto";
    }

//    @GetMapping
//    public String home(){
//        return "index";
//    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/say-hello/{name}")
    public String hello(
            @PathVariable String name,
            Model model
    ){
        model.addAttribute("name",name);
        return "hello";
    }

    @GetMapping("/say-bye")
    public String bye(
            @RequestParam String name,
            Model model
    ){
        model.addAttribute("name",name);
        return "bye";
    }
}
