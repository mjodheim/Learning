package Tableaux;

import Utils.Saisie;

import java.util.Scanner;

public class InverserTableau {
    public static void run (Scanner sc){
        int n, nbr, tmp;
        StringBuilder result = new StringBuilder("| ");

        // Définir la taille du tableau
        n = Saisie.lireEntier(
            sc,
            "Entrez la taille du tableau : ",
            valeur -> valeur > 0,
            "Erreur : le nombre doit être strictement positif."
        );

        int[] tableau = new int[n];

        // Remplir le tableau d'entiers
        for (int i = 0; i < n; i++) {

            nbr = Saisie.lireEntier(
                sc,
                "Entrez le nombre " + (i + 1) + " : ",
                "Erreur : le nombre doit être un entier."
            );

            tableau[i] = nbr;
        }

        // Inverser le tableau
        for (int i = 0; i < n/2; i++) {
            tmp = tableau[i];
            tableau[i] = tableau[n-1-i];
            tableau[n-1-i] = tmp;
        }

        // Afficher le tableau inversé
        for (int i = 0; i < n; i++) {
            if (i == n-1) result.append(tableau[i]).append(" |");
            else result.append(tableau[i]).append(" | ");
        }
        System.out.printf("""
                Tableau inversé :
                %s
                """, result);
    }
}
