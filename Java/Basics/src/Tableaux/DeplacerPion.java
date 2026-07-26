package Tableaux;

import Utils.Tableau;

import java.util.Random;
import java.util.Scanner;

public class DeplacerPion {
    public static void run (Scanner sc) {
        String[] tab = new String[10];
        Random random = new Random();
        int index = random.nextInt(10);
        String move;

        // Remplissage du tableau avec "_"
        Tableau.initialiserTableau(tab,"_");

        // placement du "pion" dans le tableau
        tab[index] = "o";

        // Afficher le tableau
        Tableau.afficherTableau(tab);

        System.out.print("Déplacement : g = gauche, d = droite, q = quitter : ");

        // Déplacement du pion
        do {
            move =  sc.nextLine().trim().toLowerCase();
            switch (move) {
                case "g" -> {
                    if (index > 0) {
                        tab[index] = "_";
                        tab[index - 1] = "o";
                        index --;
                        Tableau.afficherTableau(tab);
                    } else Tableau.afficherTableau(tab);
                }
                case "d" -> {
                    if (index < tab.length - 1) {
                        tab[index] = "_";
                        tab[index + 1] = "o";
                        index ++;
                        Tableau.afficherTableau(tab);
                    } else Tableau.afficherTableau(tab);
                }
                case "q" -> {
                    System.out.println("Retour au menu précédent...");
                }
                default -> {
                    System.out.print("Commande invalide.");
                    System.out.print("Déplacement : g = gauche, d = droite, q = quitter : ");
                }
            }
        } while (!move.equals("q"));
    }
}
