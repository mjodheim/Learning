package Calculs;

import Utils.Saisie;

import java.util.Scanner;

public final class Exposant {
    private Exposant() {
    }

    public static void run(Scanner scanner) {
        int nombre = Saisie.lireEntier(
            scanner,
            "Nombre : ",
            "Erreur : veuillez entrer un nombre entier valide."
        );

        int exposant = Saisie.lireEntier(
            scanner,
            "Exposant : ",
            "Erreur : veuillez entrer un exposant entier valide."
        );

        if (nombre == 0 && exposant < 0) {
            System.out.println("Calcul impossible : zéro ne peut pas être élevé à une puissance négative.");
            return;
        }

        double resultat = calculerPuissance(nombre, exposant);
        System.out.printf("Le résultat de %d élevé à la puissance %d vaut %s.%n",
            nombre,
            exposant,
            formaterResultat(resultat)
        );
    }

    private static double calculerPuissance(int nombre, int exposant) {
        if (exposant == 0) {
            return 1;
        }

        long exposantAbsolu = Math.abs((long) exposant);
        double resultat = 1;

        for (long i = 0; i < exposantAbsolu; i++) {
            resultat *= nombre;
        }

        return exposant < 0 ? 1 / resultat : resultat;
    }

    private static String formaterResultat(double resultat) {
        if (resultat == Math.rint(resultat)) {
            return String.format("%.0f", resultat);
        }
        return Double.toString(resultat);
    }
}
