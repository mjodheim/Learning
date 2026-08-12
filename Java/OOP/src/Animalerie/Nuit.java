package Animalerie;

import Animalerie.animaux.Animal;

import java.util.List;

public final class Nuit {
    private Nuit() {
    }

    public static void run(Pension pension) {
        if (pension.estVide()) {
            System.out.println("\nAucun animal encodé : la nuit est calme.\n");
            return;
        }

        System.out.println("\nLa nuit passe sur l'animalerie...");

        // Chaque animal tire sa propre chance avec SA probabilité :
        // 0,5 % pour un chat, 1 % pour un chien, 3 % pour un oiseau.
        List<Animal> victimes = pension.passerLaNuit();

        if (victimes.isEmpty()) {
            System.out.println("Tous les pensionnaires ont passé la nuit.");
        } else {
            System.out.println("Décès constatés au matin :");
            for (Animal victime : victimes) {
                System.out.printf(
                    "  %s (%s) — risque de décès : %.1f %%%n",
                    victime.getNom(),
                    victime.getEspece(),
                    victime.getProbabiliteDeDeces() * 100
                );
            }
        }

        System.out.printf(
            "Il reste %d animal/animaux en vie sur %d.%n%n",
            pension.compterVivants(),
            pension.getNombre()
        );
    }
}
