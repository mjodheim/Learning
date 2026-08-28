package Banque.Operations;

import Banque.Banque;
import Utils.Saisie;

import java.util.Scanner;

public final class Operations {
    private Operations() {
        // Empêche l'instanciation
    }

    public static void run(Scanner scanner,  Banque banque) {
        int choix;

        do {
            System.out.print("""
                ===== Opérations =====
                1. Effectuer un dépôt
                2. Effectuer un retrait
                3. Afficher les avoirs d'un compte
                4. Appliquer les intérêts à tous les comptes
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
                case 1 -> Depot.run(scanner, banque);
                case 2 -> Retrait.run(scanner, banque);
                case 3 -> Afficher.run(scanner, banque);
                case 4 -> Interets.run(scanner, banque);
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }
}
