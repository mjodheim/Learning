package Calculs;

import Utils.Saisie;

import java.util.Scanner;

public final class TableMultiplication {
    private TableMultiplication() {
    }

    public static void run(Scanner scanner) {
        int choix;

        do {
            System.out.print("""
                === Menu des tables de multiplication ===
                1. Table au choix
                2. Toutes les tables de 1 à 9
                0. Quitter
                """);

            choix = Saisie.lireEntier(
                scanner,
                "Votre choix : ",
                "Choix invalide.",
                0,
                2
            );

            switch (choix) {
                case 1 -> afficherTableAuChoix(scanner);
                case 2 -> afficherToutesLesTables();
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }

    private static void afficherTableAuChoix(Scanner scanner) {
        int table = Saisie.lireEntier(
            scanner,
            "Quelle table veux-tu afficher ? ",
            "Veuillez entrer un nombre entier."
        );

        afficherTable(table);
    }

    private static void afficherToutesLesTables() {
        System.out.println("--- Tables de multiplication de 1 à 9 ---");
        for (int table = 1; table <= 9; table++) {
            afficherTable(table);
            System.out.println();
        }
    }

    private static void afficherTable(int table) {
        for (int multiplicateur = 1; multiplicateur <= 10; multiplicateur++) {
            System.out.printf("%d × %d = %d%n", table, multiplicateur, table * multiplicateur);
        }
    }
}
