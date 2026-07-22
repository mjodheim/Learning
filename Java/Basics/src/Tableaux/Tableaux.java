package Tableaux;

import Utils.Saisie;

import java.util.Scanner;

public class Tableaux {
    public static void run(Scanner scanner) {
        int choix;

        do {
            System.out.print("""
                ===== Choix de l'exercice =====
                1. Distributeur de boissons
                2. Inverser un tableau
                3. Tableau de dix entiers
                4. Tri de deux tableaux d'entiers aléatoires
                0. Revenir au menu principal
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                4
            );

            switch (choix) {
                case 1 -> DistributeurDeBoissons.run(scanner);
                case 2 -> InverserTableau.run(scanner);
                case 3 -> TableauDixEntiers.run();
                case 4 -> TriTableaux.run();
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }
}
