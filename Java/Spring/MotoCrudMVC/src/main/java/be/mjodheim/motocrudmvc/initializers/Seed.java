package be.mjodheim.motocrudmvc.initializers;

import be.mjodheim.motocrudmvc.entities.Category;
import be.mjodheim.motocrudmvc.entities.Moto;
import be.mjodheim.motocrudmvc.repositories.CategoryRepository;
import be.mjodheim.motocrudmvc.repositories.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Seed implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final MotoRepository motoRepository;

    @Override
    public void run(String... args) throws Exception {

        // On sort si on a déjà des données présentes

        if (categoryRepository.count() > 0 || motoRepository.count() > 0) {
            return;
        }

        // Category

        Category sportive = new Category("Sportive");
        Category trail =  new Category("Trail");
        Category custom = new Category("Custom");
        Category touring = new Category("Touring");
        Category supermotard = new Category("Supermotard");
        Category roadster = new Category("Roadster");

        List<Category> categories = List.of(
                sportive, trail, custom, touring, supermotard, roadster
        );

        categoryRepository.saveAll(categories);

        // Motos

        List<Moto> motos = List.of(
                new Moto(
                        "Honda",
                        "CBR 600 RR",
                        599,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/Honda_CBR_600_RR.jpg?width=1200",
                        sportive
                ),
                new Moto(
                        "Yamaha",
                        "MT-07",
                        689,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/Yamaha_MT-07.jpg?width=1200",
                        roadster
                ),
                new Moto(
                        "Kawasaki",
                        "Z900",
                        948,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/KawasakiZ900.jpg?width=1200",
                        roadster
                ),
                new Moto(
                        "Suzuki",
                        "GSX-8S",
                        776,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/Suzuki_GSX-8S.png?width=1200",
                        roadster
                ),
                new Moto(
                        "Ducati",
                        "Monster",
                        937,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/Monster%2B_937_a.jpg?width=1200",
                        roadster
                ),
                new Moto(
                        "BMW",
                        "S 1000 RR",
                        999,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/BMW_S1000_RR_2025.jpg?width=1200",
                        sportive
                )
        );

        motoRepository.saveAll(motos);
    }
}
