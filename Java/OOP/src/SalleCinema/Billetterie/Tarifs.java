package SalleCinema.Billetterie;

import SalleCinema.cinema.Salle;
import Utils.Saisie;

import java.util.Scanner;

public final class Tarifs {
    private Tarifs() {
    }

    public static void run(Scanner scanner) {
        double ht = Saisie.lireReel(
            scanner,
            "Montant hors taxes : ",
            "Valeur invalide, veuillez ré-essayer..."
        );

        // Aucune instance n'est nécessaire : la méthode est appelée sur la classe.
        System.out.printf("Salle.prixTTC(%.2f) -> %.2f € TTC%n%n", ht, Salle.prixTTC(ht));
    }
}
