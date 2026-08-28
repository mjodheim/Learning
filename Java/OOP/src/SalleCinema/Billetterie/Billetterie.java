package SalleCinema.Billetterie;

import SalleCinema.cinema.Salle;
import Utils.Saisie;

import java.util.Scanner;

public final class Billetterie {
    private Billetterie() {
    }

    public static void run(Scanner scanner, Salle salle) {
        int choix;

        do {
            System.out.print("""
                --- Billetterie ---
                1. Vendre des places
                2. Tenter des réservations invalides
                3. Encaisser la caisse
                4. Consulter les tarifs
                0. Retour
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                4
            );

            switch (choix) {
                case 1 -> Guichet.run(scanner, salle);
                case 2 -> ReservationsRefusees.run(salle);
                case 3 -> Caisse.run(scanner, salle);
                case 4 -> Tarifs.run(scanner);
                default -> System.out.println("Retour au menu de la salle...");
            }
        } while (choix != 0);
    }
}
