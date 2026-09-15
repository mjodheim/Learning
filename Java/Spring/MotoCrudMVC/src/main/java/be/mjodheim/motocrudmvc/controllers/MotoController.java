package be.mjodheim.motocrudmvc.controllers;

import be.mjodheim.motocrudmvc.entities.Category;
import be.mjodheim.motocrudmvc.entities.Equipment;
import be.mjodheim.motocrudmvc.entities.Moto;
import be.mjodheim.motocrudmvc.mappers.MotoMapper;
import be.mjodheim.motocrudmvc.models.MotoFilter;
import be.mjodheim.motocrudmvc.models.category.CategoryDto;
import be.mjodheim.motocrudmvc.models.equipment.EquipmentDto;
import be.mjodheim.motocrudmvc.models.moto.MotoForm;
import be.mjodheim.motocrudmvc.models.moto.MotoIndexDto;
import be.mjodheim.motocrudmvc.repositories.CategoryRepository;
import be.mjodheim.motocrudmvc.repositories.EquipmentRepository;
import be.mjodheim.motocrudmvc.repositories.MotoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/motos")
@RequiredArgsConstructor
public class MotoController {

    private final MotoRepository motoRepository;
    private final CategoryRepository categoryRepository;
    private final EquipmentRepository equipmentRepository;
    private final MotoMapper motoMapper;

    @GetMapping
    public String index(
            @ModelAttribute MotoFilter filter,
            Model model
    ) {
        List<MotoIndexDto> motos = motoRepository.findWithFilter(filter.brand(), filter.categoryId())
                .stream()
                .map(motoMapper::toIndexDto)
                .toList();

        List<CategoryDto> categories = categoryRepository.findAll()
                .stream()
                .map(motoMapper::toCategoryDto)
                .sorted(Comparator.comparing(CategoryDto::name))
                .toList();

        model.addAttribute("motos", motos);
        model.addAttribute("brands", motoRepository.findAllBrands());
        model.addAttribute("categories", categories);
        model.addAttribute("filter", filter);

        return "moto/index";
    }

    @GetMapping("/{id}")
    public String details(
            @PathVariable Long id,
            Model model
    ) {
        Moto moto = motoRepository.findById(id).orElseThrow();

        model.addAttribute("moto", motoMapper.toDetailsDto(moto));
        return "moto/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("moto", new MotoForm());
        addFormData(model);

        return "moto/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute(name = "moto") MotoForm form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            addFormData(model);
            return "moto/create";
        }

        Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow();
        Set<Equipment> equipments = getEquipments(form.getEquipmentIds());

        Moto moto = motoMapper.toEntity(form, category, equipments);
        motoRepository.save(moto);

        return "redirect:/motos";
    }

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            Model model
    ) {
        Moto moto = motoRepository.findById(id).orElseThrow();

        model.addAttribute("moto", motoMapper.toForm(moto));
        model.addAttribute("motoId", id);
        addFormData(model);

        return "moto/update";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute(name = "moto") MotoForm form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("motoId", id);
            addFormData(model);
            return "moto/update";
        }

        Moto moto = motoRepository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow();
        Set<Equipment> equipments = getEquipments(form.getEquipmentIds());

        motoMapper.updateEntity(moto, form, category, equipments);
        motoRepository.save(moto);

        return "redirect:/motos/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (!motoRepository.existsById(id)) {
            throw new RuntimeException("Moto not found");
        }

        motoRepository.deleteById(id);

        return "redirect:/motos";
    }

    private void addFormData(Model model) {
        List<CategoryDto> categories = categoryRepository.findAll()
                .stream()
                .map(motoMapper::toCategoryDto)
                .sorted(Comparator.comparing(CategoryDto::name))
                .toList();

        List<EquipmentDto> equipments = equipmentRepository.findAll()
                .stream()
                .map(motoMapper::toEquipmentDto)
                .sorted(Comparator.comparing(EquipmentDto::name))
                .toList();

        model.addAttribute("categories", categories);
        model.addAttribute("equipments", equipments);
    }

    private Set<Equipment> getEquipments(Set<Long> equipmentIds) {
        if (equipmentIds == null || equipmentIds.isEmpty()) {
            return new HashSet<>();
        }

        return new HashSet<>(equipmentRepository.findAllById(equipmentIds));
    }
}
