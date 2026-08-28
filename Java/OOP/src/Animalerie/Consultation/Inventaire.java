package Animalerie.Consultation;

import Animalerie.FicheAnimal;
import Animalerie.Pension;
import Animalerie.animaux.Animal;

public final class Inventaire {
    private Inventaire() {
    }

    public static void run(Pension pension) {
        if (pension.estVide()) {
            System.out.println("\nAucun animal encodé pour l'instant.\n");
            return;
        }

        System.out.printf("%nRegistre de l'animalerie (%d animal/animaux) :%n%n", pension.getNombre());

        // Une seule boucle pour les trois espèces : chaque fiche s'affiche
        // avec les caractéristiques de son espèce, sans un seul instanceof ici.
        for (Animal animal : pension.getAnimaux()) {
            FicheAnimal.run(animal);
        }
    }
}
