package Animalerie.Consultation;

import Animalerie.Pension;
import Animalerie.animaux.Animal;

public final class Concert {
    private Concert() {
    }

    public static void run(Pension pension) {
        if (pension.estVide()) {
            System.out.println("\nAucun animal encodé : l'animalerie est silencieuse.\n");
            return;
        }

        System.out.println("\nTout le monde crie :");

        for (Animal animal : pension.getAnimaux()) {
            if (!animal.estVivant()) {
                System.out.printf("  %s (%s) ne crie plus.%n", animal.getNom(), animal.getEspece());
                continue;
            }

            // Même appel pour tout le monde : c'est le type réel de l'objet
            // qui choisit entre « Miaou ! », « Wouf ! » et « Cui-cui ! ».
            System.out.printf(
                "  %s (%s) : « %s »%n",
                animal.getNom(),
                animal.getEspece(),
                animal.crier()
            );
        }

        System.out.println();
    }
}
