package SalleCinema.Direction;

import SalleCinema.cinema.Salle;
import SalleCinema.cinema.premium.SalleVIP;
import Utils.Saisie;

import java.util.Scanner;

public final class Direction {
    private Direction() {
    }

    public static void run(Scanner scanner, Salle salle, SalleVIP vip) {
        int choix;

        do {
            System.out.print("""
                --- Direction ---
                1. Rapport des salles
                2. Habilitations du personnel
                0. Retour
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                2
            );

            switch (choix) {
                case 1 -> RapportDesSalles.run(salle, vip);
                case 2 -> Habilitations.run(salle);
                default -> System.out.println("Retour au menu de la salle...");
            }
        } while (choix != 0);
    }
}
