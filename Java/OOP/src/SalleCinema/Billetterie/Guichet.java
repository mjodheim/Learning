package SalleCinema.Billetterie;

import SalleCinema.EtatSalle;
import SalleCinema.cinema.Salle;
import Utils.Saisie;

import java.util.Scanner;

public final class Guichet {
    private Guichet() {
    }

    public static void run(Scanner scanner, Salle salle) {
        System.out.print("""
            Quel type de vente ?
            1. Une place, plein tarif
            2. Plusieurs places, plein tarif
            3. Plusieurs places, avec réduction
            0. Retour
            """);

        int choix = Saisie.lireEntier(
            scanner,
            "",
            "Choix invalide, veuillez ré-essayer...",
            0,
            3
        );

        if (choix == 0) {
            return;
        }

        // Les trois cas de vente correspondent aux trois surcharges de reserver().
        // Seules les deux premières délèguent : tout le travail est fait par
        // reserver(nb, reduction).
        boolean vendu = switch (choix) {
            case 1 -> salle.reserver();
            case 2 -> salle.reserver(lireNombreDePlaces(scanner));
            default -> salle.reserver(lireNombreDePlaces(scanner), lireReduction(scanner));
        };

        System.out.println(vendu ? "Vente enregistrée." : "Vente refusée.");
        EtatSalle.run(salle);
    }

    private static int lireNombreDePlaces(Scanner scanner) {
        return Saisie.lireEntier(
            scanner,
            "Nombre de places : ",
            "Valeur invalide, veuillez ré-essayer..."
        );
    }

    private static double lireReduction(Scanner scanner) {
        return Saisie.lireReel(
            scanner,
            "Réduction (0 = plein tarif, 0.5 = -50%) : ",
            "Valeur invalide, veuillez ré-essayer..."
        );
    }
}
