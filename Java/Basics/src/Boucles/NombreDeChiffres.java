package Boucles;

import Utils.Saisie;

import java.util.Scanner;

public final class NombreDeChiffres {
    private NombreDeChiffres() {
    }

    public static void run(Scanner scanner) {
        int nombre = Saisie.lireEntier(
            scanner,
            "Entrez un nombre entier : ",
            "Erreur : veuillez entrer un nombre entier valide."
        );

        int valeurAbsolue = Math.abs(nombre);
        int nombreDeChiffres = valeurAbsolue == 0 ? 1 : 0;

        while (valeurAbsolue > 0) {
            nombreDeChiffres++;
            valeurAbsolue /= 10;
        }

        System.out.printf("Le nombre %d est composé de %d chiffre(s).%n", nombre, nombreDeChiffres);
    }
}
