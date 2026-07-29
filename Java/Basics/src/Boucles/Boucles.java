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
                2. Factorielle d'un nombre
                3. Nombre de chiffres
                4 Nombre de voyelles
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
                case 1 -> NombreDeLignes.run(scanner);
                case 2 -> Factorielle.run(scanner);
                case 3 -> NombreDeChiffres.run(scanner);
                case 4 -> NombreDeVoyelles.run(scanner);
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }
}
