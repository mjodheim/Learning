package Boucles;

import Utils.Saisie;

import java.util.Scanner;

public final class Factorielle {
    private Factorielle() {
    }

    public static void run(Scanner scanner) {
        int nombre = Saisie.lireEntier(
            scanner,
            "Entrez un nombre entier positif (0 à 20) : ",
            valeur -> valeur >= 0 && valeur <= 20,
            "Erreur : veuillez entrer un entier compris entre 0 et 20."
        );

        long resultat = 1;
        for (int i = 2; i <= nombre; i++) {
            resultat *= i;
        }

        System.out.printf("La factorielle de %d vaut %d.%n", nombre, resultat);
    }
}
