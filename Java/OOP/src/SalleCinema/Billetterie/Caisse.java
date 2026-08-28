package SalleCinema.Billetterie;

import SalleCinema.cinema.Salle;
import Utils.Saisie;

import java.util.Scanner;

public final class Caisse {
    private Caisse() {
    }

    public static void run(Scanner scanner, Salle salle) {
        System.out.println("La caisse accepte zéro, un ou plusieurs billets à la fois :");

        // Le même nom de méthode accepte n'importe quel nombre d'arguments.
        System.out.printf("aucun billet          -> %.2f €%n", salle.encaisser());
        System.out.printf("un billet de 20       -> %.2f €%n", salle.encaisser(20));
        System.out.printf("20 + 10 + 5           -> %.2f €%n%n", salle.encaisser(20, 10, 5));

        int nombreDeBillets = Saisie.lireEntier(
            scanner,
            "Combien de billets voulez-vous encaisser ? ",
            "Valeur invalide, veuillez ré-essayer...",
            0,
            10
        );

        // À l'intérieur de la méthode, billets est un simple tableau :
        // on peut donc aussi lui passer un tableau construit à la main.
        double[] billets = new double[nombreDeBillets];
        for (int i = 0; i < nombreDeBillets; i++) {
            billets[i] = Saisie.lireReel(
                scanner,
                "Billet n°" + (i + 1) + " : ",
                "Valeur invalide, veuillez ré-essayer..."
            );
        }

        // encaisser rend le total sans rien modifier : la recette de la salle
        // ne bouge pas, seul reserver l'alimente.
        System.out.printf("Total encaissé : %.2f €%n%n", salle.encaisser(billets));
    }
}
