package Matrices;

import java.util.Random;

public class PlusGrandElement {
    public static void run(){
        Random random = new Random();
        int taille = random.nextInt(10);
        System.out.print("Création d'une matrice de taille " + taille + "...");

        int[][] matrice = new int[taille][taille];
        int max = 0;
        int x = 0, y = 0;
        StringBuilder matriceString = new StringBuilder();


        // On remplit la matrice avec des nombres aléatoires de 0 à 9 et on les affiche.
        // On récupère en même temps le plus grand élément de chaque ligne.

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = random.nextInt(10);
                matriceString.append(matrice[i][j]).append(" | ");
                if (matrice[i][j] > max) {
                    max = matrice[i][j];
                    x = i;
                    y = j;
                }
            }
            System.out.println(matriceString);
        }

        // Affichage
        System.out.printf("Max = %d en (%d, %d)", max, x, y);
    }
}
