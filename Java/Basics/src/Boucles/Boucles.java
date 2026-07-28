package Boucles;

import Utils.Saisie;

import java.util.Scanner;

public class Boucles {
    public static void run(Scanner scanner) {
        int choix;

        do {
            System.out.print("""
                ===== Choix de l'exercice =====
                1. Nombre de lignes
                0. Revenir au menu principal
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                1
            );

            if (choix == 1) {
                NombreDeLignes.run(scanner);
            } else {
                System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }
}
