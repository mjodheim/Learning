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
                        "Une supersport mythique, précise et affûtée. Son quatre-cylindres de 599 cm³ aime prendre des tours et son châssis est taillé pour les routes sinueuses comme pour la piste. La CBR 600 RR privilégie les sensations, la précision et une vraie position de sportive.",
                        sportive
                ),
                new Moto(
                        "Yamaha",
                        "MT-07",
                        689,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/Yamaha_MT-07.jpg?width=1200",
                        "Légère, joueuse et facile à prendre en main, la MT-07 est devenue une référence des roadsters. Son bicylindre CP2 de 689 cm³ offre beaucoup de couple à bas et mi-régime, avec un caractère vivant. Une moto idéale pour le quotidien sans sacrifier le plaisir.",
                        roadster
                ),
                new Moto(
                        "Kawasaki",
                        "Z900",
                        948,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/KawasakiZ900.jpg?width=1200",
                        "La Z900 combine le caractère d'un gros roadster avec la douceur d'un quatre-cylindres de 948 cm³. Puissante, souple et très expressive, elle offre de fortes accélérations tout en restant agréable au quotidien. Son style Sugomi lui donne une présence immédiatement reconnaissable.",
                        roadster
                ),
                new Moto(
                        "Suzuki",
                        "GSX-8S",
                        776,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/Suzuki_GSX-8S.png?width=1200",
                        "Moderne et polyvalente, la GSX-8S utilise un bicylindre parallèle de 776 cm³ à calage 270°. Elle se distingue par son moteur coupleux, sa facilité de conduite et son comportement équilibré. Un roadster efficace aussi bien en ville que sur les petites routes.",
                        roadster
                ),
                new Moto(
                        "Ducati",
                        "Monster",
                        937,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/Monster%2B_937_a.jpg?width=1200",
                        "La Monster reste fidèle à la recette Ducati : un roadster léger, compact et plein de caractère. Son bicylindre Testastretta 11° de 937 cm³ délivre un couple généreux et une sonorité typique. Agile et sportive, elle mélange design italien, électronique moderne et sensations.",
                        roadster
                ),
                new Moto(
                        "BMW",
                        "S 1000 RR",
                        999,
                        "https://commons.wikimedia.org/wiki/Special:Redirect/file/BMW_S1000_RR_2025.jpg?width=1200",
                        "Une superbike conçue avec la performance en priorité. Son quatre-cylindres de 999 cm³ délivre une puissance impressionnante, épaulée par une électronique très avancée et la technologie BMW ShiftCam. Rapide, précise et radicale, la S 1000 RR est avant tout une machine taillée pour l'attaque.",
                        sportive
                )
        );

        motoRepository.saveAll(motos);
    }
}
