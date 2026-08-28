package Matrices;

import Utils.Saisie;

import java.util.Scanner;

public class Matrices {
    public static void run(Scanner scanner) {
        int choix;

        do {
            System.out.print("""
                ===== Choix de l'exercice =====
                1. Somme de chaque ligne (matrice 3x3)
                2. Plus grand Element et position
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
                case 1 -> SommeMatrice3x3.run();
                case 2 -> PlusGrandElement.run();
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }
}
