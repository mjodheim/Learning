package MiniApplications;

import Utils.Saisie;

import java.util.Scanner;

public final class MoyenneDesScores {
    private MoyenneDesScores() {
    }

    public static void run(Scanner scanner) {
        int nombreDeJoueurs = Saisie.lireEntier(
            scanner,
            "Nombre de joueurs (max 10) : ",
            "Erreur : veuillez entrer un nombre compris entre 1 et 10.",
            1,
            10
        );

        float somme = 0;
        for (int i = 0; i < nombreDeJoueurs; i++) {
            somme += Saisie.lireFlottant(
                scanner,
                "Score du joueur " + (i + 1) + " : ",
                "Erreur : veuillez entrer un score valide."
            );
        }

        float moyenne = somme / nombreDeJoueurs;
        System.out.printf("Moyenne des scores : %.2f%n", moyenne);
    }
}
