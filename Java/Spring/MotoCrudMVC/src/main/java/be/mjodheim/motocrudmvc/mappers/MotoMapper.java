package be.mjodheim.motocrudmvc.mappers;

import be.mjodheim.motocrudmvc.entities.Category;
import be.mjodheim.motocrudmvc.entities.Equipment;
import be.mjodheim.motocrudmvc.entities.Moto;
import be.mjodheim.motocrudmvc.entities.TechnicalSheet;
import be.mjodheim.motocrudmvc.models.category.CategoryDto;
import be.mjodheim.motocrudmvc.models.equipment.EquipmentDto;
import be.mjodheim.motocrudmvc.models.moto.MotoDetailsDto;
import be.mjodheim.motocrudmvc.models.moto.MotoForm;
import be.mjodheim.motocrudmvc.models.moto.MotoIndexDto;
import be.mjodheim.motocrudmvc.models.moto.TechnicalSheetDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MotoMapper {

    public MotoIndexDto toIndexDto(Moto moto) {
        return new MotoIndexDto(
                moto.getId(),
                moto.getBrand(),
                moto.getModel(),
                moto.getCc(),
                moto.getImageUrl(),
                toCategoryDto(moto.getCategory())
        );
    }

    public MotoDetailsDto toDetailsDto(Moto moto) {
        return new MotoDetailsDto(
                moto.getId(),
                moto.getBrand(),
                moto.getModel(),
                moto.getCc(),
                moto.getImageUrl(),
                moto.getDescription(),
                toCategoryDto(moto.getCategory()),
                toTechnicalSheetDto(moto.getTechnicalSheet()),
                moto.getEquipments().stream()
                        .map(this::toEquipmentDto)
                        .sorted(Comparator.comparing(EquipmentDto::name))
                        .toList()
        );
    }

    public MotoForm toForm(Moto moto) {
        MotoForm form = new MotoForm();

        form.setBrand(moto.getBrand());
        form.setModel(moto.getModel());
        form.setCc(moto.getCc());
        form.setImageUrl(moto.getImageUrl());
        form.setDescription(moto.getDescription());
        form.setCategoryId(moto.getCategory().getId());
        form.setEquipmentIds(
                moto.getEquipments().stream()
                        .map(Equipment::getId)
                        .collect(Collectors.toSet())
        );

        if (moto.getTechnicalSheet() != null) {
            form.setHorsepower(moto.getTechnicalSheet().getHorsepower());
            form.setWeightKg(moto.getTechnicalSheet().getWeightKg());
            form.setTankCapacity(moto.getTechnicalSheet().getTankCapacity());
        }

        return form;
    }

    public Moto toEntity(MotoForm form, Category category, Set<Equipment> equipments) {
        Moto moto = new Moto(
                form.getBrand(),
                form.getModel(),
                form.getCc(),
                form.getImageUrl(),
                form.getDescription(),
                category
        );

        moto.setTechnicalSheet(toTechnicalSheet(form));
        moto.setEquipments(equipments);

        return moto;
    }

    public void updateEntity(Moto moto, MotoForm form, Category category, Set<Equipment> equipments) {
        moto.setBrand(form.getBrand());
        moto.setModel(form.getModel());
        moto.setCc(form.getCc());
        moto.setImageUrl(form.getImageUrl());
        moto.setDescription(form.getDescription());
        moto.setCategory(category);
        moto.setEquipments(equipments);

        if (!hasTechnicalSheetData(form)) {
            moto.setTechnicalSheet(null);
            return;
        }

        TechnicalSheet technicalSheet = moto.getTechnicalSheet();

        if (technicalSheet == null) {
            moto.setTechnicalSheet(toTechnicalSheet(form));
            return;
        }

        technicalSheet.setHorsepower(form.getHorsepower());
        technicalSheet.setWeightKg(form.getWeightKg());
        technicalSheet.setTankCapacity(form.getTankCapacity());
    }

    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    public EquipmentDto toEquipmentDto(Equipment equipment) {
        return new EquipmentDto(
                equipment.getId(),
                equipment.getName()
        );
    }

    private TechnicalSheetDto toTechnicalSheetDto(TechnicalSheet technicalSheet) {
        if (technicalSheet == null) {
            return null;
        }

        return new TechnicalSheetDto(
                technicalSheet.getHorsepower(),
                technicalSheet.getWeightKg(),
                technicalSheet.getTankCapacity()
        );
    }

    private TechnicalSheet toTechnicalSheet(MotoForm form) {
        if (!hasTechnicalSheetData(form)) {
            return null;
        }

        return new TechnicalSheet(
                form.getHorsepower(),
                form.getWeightKg(),
                form.getTankCapacity()
        );
    }

    private boolean hasTechnicalSheetData(MotoForm form) {
        return form.getHorsepower() != null
                || form.getWeightKg() != null
                || form.getTankCapacity() != null;
    }
}
