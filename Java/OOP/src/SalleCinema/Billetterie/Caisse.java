package SalleCinema.Billetterie;

import SalleCinema.EtatSalle;
import SalleCinema.cinema.Salle;
import Utils.Saisie;

import java.util.Scanner;

public final class Caisse {
    private Caisse() {
    }

    public static void run(Scanner scanner, Salle salle) {
        System.out.println("""
            La caisse accepte zéro, un ou plusieurs billets à la fois,
            et chaque encaissement vient créditer la recette de la salle :""");

        // Le même nom de méthode accepte n'importe quel nombre d'arguments.
        // Ces trois appels sont de vrais encaissements : ils créditent 55 € au total.
        System.out.printf("aucun billet          -> %.2f €%n", salle.encaisser());
        System.out.printf("un billet de 20       -> %.2f €%n", salle.encaisser(20));
        System.out.printf("20 + 10 + 5           -> %.2f €%n", salle.encaisser(20, 10, 5));

        // La garde renvoie 0 sans rien créditer : un seul billet négatif
        // suffit à faire refuser le lot entier, le 20 compris.
        System.out.printf("20 + (-5)             -> %.2f €   (refusé)%n%n", salle.encaisser(20, -5));

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

        double total = salle.encaisser(billets);

        // encaisser renvoie 0 aussi bien pour un lot refusé que pour un lot
        // réellement vide : c'est billetsValides qui permet de trancher.
        if (Salle.billetsValides(billets)) {
            System.out.printf("Total encaissé : %.2f €%n", total);
        } else {
            System.out.println("Encaissement refusé : le lot contient au moins un billet négatif.");
        }

        // La recette affichée ici cumule les ventes au guichet et les encaissements.
        EtatSalle.run(salle);
    }
}
