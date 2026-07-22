package MiniApplications;

import Utils.Saisie;

import java.util.Scanner;

public class MiniApplications {
    public static void run(Scanner scanner) {
        int choix;

        do {
            System.out.print("""
                ===== Choix de l'exercice =====
                1. Moyenne des scores
                2. Plus ou moins ?
                3. Système de connexion
                0. Revenir au menu principal
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                3
            );

            switch (choix) {
                case 1 -> MoyenneDesScores.run(scanner);
                case 2 -> PlusOuMoins.run(scanner);
                case 3 -> SystemeDeConnexion.run(scanner);
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }
}
