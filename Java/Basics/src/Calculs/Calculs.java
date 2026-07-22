package Calculs;

import Utils.Saisie;

import java.util.Scanner;

public class Calculs {
    public static void run(Scanner scanner) {
        int choix;

        do {
            System.out.print("""
                ===== Choix de l'exercice =====
                1. Exposant
                2. Table de multiplication
                0. Revenir au menu principal
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                2
            );

            switch (choix) {
                case 1 -> Exposant.run(scanner);
                case 2 -> TableMultiplication.run(scanner);
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }
}
