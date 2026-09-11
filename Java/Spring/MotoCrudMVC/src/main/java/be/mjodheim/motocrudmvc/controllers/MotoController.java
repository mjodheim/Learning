package be.mjodheim.motocrudmvc.controllers;

import be.mjodheim.motocrudmvc.datas.FakeDb;
import be.mjodheim.motocrudmvc.models.Moto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motos")
public class MotoController {

    @GetMapping
    public String index(
            @RequestParam(required = false) String brand,
            Model model
    ) {
        List<Moto> motos = (brand == null || brand.isBlank())
                ? FakeDb.motos
                : FakeDb.motos.stream()
                .filter(moto -> brand.equalsIgnoreCase(moto.getBrand()))
                .toList();

        List<String> brands = FakeDb.motos.stream()
                .map(Moto::getBrand)
                .distinct()
                .sorted()
                .toList();

        model.addAttribute("motos", motos);
        model.addAttribute("brands", brands);
        model.addAttribute("brand", brand);

        return "moto/index";
    }

    @GetMapping("/{id}")
    public String details(
            @PathVariable Long id,
            Model model
    ) {
        Moto moto = FakeDb.motos.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow();

        model.addAttribute("moto", moto);
        return "moto/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("moto", new Moto());
        return "moto/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute Moto moto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "moto/create";
        }

        moto.setId(FakeDb.nextId());
        FakeDb.motos.add(moto);

        return "redirect:/motos";
    }

    @GetMapping("/{id}/update")
    public String update(
            @PathVariable Long id,
            Model model
    ) {
        Moto moto = FakeDb.motos.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow();

        model.addAttribute("moto", moto);
        return "moto/update";
    }

    @PostMapping("/{id}/update")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute Moto moto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            moto.setId(id);
            return "moto/update";
        }

        Moto existingMoto = FakeDb.motos.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow();

        existingMoto.setBrand(moto.getBrand());
        existingMoto.setModel(moto.getModel());
        existingMoto.setCc(moto.getCc());
        existingMoto.setImageUrl(moto.getImageUrl());

        return "redirect:/motos/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        FakeDb.motos.removeIf(moto -> moto.getId().equals(id));
        return "redirect:/motos";
    }
}
