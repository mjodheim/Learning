package Boucles;

import java.util.Scanner;

public class NombreDeLignes {
    public static void run (Scanner sc){
        String saisie;
        int nbLignes, count = 0;

        sc.nextLine(); // Consomme le retour à la ligne restant

        System.out.print("Veuillez entrer un nombre de lignes à afficher (max 20): ");
        saisie = sc.nextLine();

        try {
            nbLignes = Integer.parseInt(saisie);
            if (nbLignes < 1 || nbLignes > 20) throw new NumberFormatException();

            do {
                int nbEtoiles = 0;

                do {
                    System.out.print("*");
                    nbEtoiles++;
                } while (nbEtoiles <= count);

                count++;
            } while(count < nbLignes);

        } catch (NumberFormatException e) {
            System.err.printf("Erreur %s : nombre positif inférieur ou égal à 20 attendu\n", e.getMessage());
        }

        System.out.print("Retour au menu principal...");
    }
}
