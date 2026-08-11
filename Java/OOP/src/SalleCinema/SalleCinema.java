package SalleCinema;

import SalleCinema.Billetterie.Billetterie;
import SalleCinema.Direction.Direction;
import SalleCinema.Exploitation.Exploitation;
import SalleCinema.cinema.Salle;
import SalleCinema.cinema.premium.SalleVIP;
import Utils.Saisie;

import java.util.Scanner;

public final class SalleCinema {
    private SalleCinema() {
    }

    public static void run(Scanner scanner) {
        // Création des salles.
        Salle salle = new Salle("Salle 1", 85, 4210);
        SalleVIP vip = new SalleVIP("Salle Or", 85, 7788, 40);

        int choix;

        do {
            System.out.print("""
                ===== Salle de cinéma =====
                1. Billetterie
                2. Exploitation
                3. Direction
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
                case 1 -> Billetterie.run(scanner, salle);
                case 2 -> Exploitation.run(scanner, salle, vip);
                case 3 -> Direction.run(scanner, salle, vip);
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }
}
