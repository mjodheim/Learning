package Boucles;

import Utils.Saisie;

import java.util.Scanner;

public final class NombreDeLignes {
    private NombreDeLignes() {
    }

    public static void run(Scanner scanner) {
        int nombreDeLignes = Saisie.lireEntier(
            scanner,
            "Veuillez entrer un nombre de lignes à afficher (max 20) : ",
            "Erreur : veuillez entrer un nombre compris entre 1 et 20.",
            1,
            20
        );

        for (int ligne = 1; ligne <= nombreDeLignes; ligne++) {
            System.out.println("*".repeat(ligne));
        }
    }
}
