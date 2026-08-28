package Animalerie.Consultation;

import Animalerie.Pension;
import Utils.Saisie;

import java.util.Scanner;

public final class Consultation {
    private Consultation() {
    }

    public static void run(Scanner scanner, Pension pension) {
        int choix;

        do {
            System.out.print("""
                --- Consultation ---
                1. Lister tous les animaux
                2. Compter les animaux par espèce
                3. Faire crier tous les animaux
                0. Retour
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                3
            );

            switch (choix) {
                case 1 -> Inventaire.run(pension);
                case 2 -> Recensement.run(pension);
                case 3 -> Concert.run(pension);
                default -> System.out.println("Retour au menu de l'animalerie...");
            }
        } while (choix != 0);
    }
}
