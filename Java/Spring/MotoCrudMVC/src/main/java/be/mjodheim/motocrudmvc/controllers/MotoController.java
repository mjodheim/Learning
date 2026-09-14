package be.mjodheim.motocrudmvc.controllers;

import be.mjodheim.motocrudmvc.entities.Category;
import be.mjodheim.motocrudmvc.entities.Moto;
import be.mjodheim.motocrudmvc.repositories.CategoryRepository;
import be.mjodheim.motocrudmvc.repositories.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoRepository motoRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String index(
            @RequestParam(required = false) String brand,
            Model model
    ) {
        List<Moto> allMotos = motoRepository.findAll();

        List<Moto> motos = (brand == null || brand.isBlank())
                ? allMotos
                : allMotos.stream()
                .filter(moto -> brand.equalsIgnoreCase(moto.getBrand()))
                .toList();

        List<String> brands = allMotos.stream()
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
        Moto moto = motoRepository.findById(id).orElseThrow();

        model.addAttribute("moto", moto);
        return "moto/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("moto", new Moto());
        model.addAttribute("categories", categoryRepository.findAll());
        return "moto/create";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute Moto moto,
            BindingResult bindingResult,
            @RequestParam Long categoryId,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "moto/create";
        }

        Category category = categoryRepository.findById(categoryId).orElseThrow();
        moto.setCategory(category);

        motoRepository.save(moto);

        return "redirect:/motos";
    }

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            Model model
    ) {
        Moto moto = motoRepository.findById(id).orElseThrow();

        model.addAttribute("moto", moto);
        model.addAttribute("categories", categoryRepository.findAll());
        return "moto/update";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @ModelAttribute Moto moto,
            BindingResult bindingResult,
            @RequestParam Long categoryId,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "moto/update";
        }

        Moto existingMoto = motoRepository.findById(id)
                .orElseThrow();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow();

        existingMoto.setBrand(moto.getBrand());
        existingMoto.setModel(moto.getModel());
        existingMoto.setCc(moto.getCc());
        existingMoto.setImageUrl(moto.getImageUrl());
        existingMoto.setCategory(category);
        existingMoto.setDescription(moto.getDescription());

        motoRepository.save(existingMoto);

        return "redirect:/motos/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Moto existingMoto = motoRepository.findById(id).orElseThrow();

        motoRepository.delete(existingMoto);

        return "redirect:/motos";
    }
}
