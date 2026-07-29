package Matrices;

import java.util.Random;

public class SommeMatrice3x3 {
    public static void run() {
        int[][] matrice = new int[3][3];
        int[] result = new int[3];
        StringBuilder matriceString = new StringBuilder();

        Random random = new Random();

        // On remplit la matrice avec des nombres aléatoires de 0 à 9 et on les affiche.
        // On récupère en même temps la somme de chaque ligne.

        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = random.nextInt(10);
                matriceString.append(matrice[i][j]).append(" | ");
                result[i] += matrice[i][j];
            }
            System.out.println(matriceString);
        }

        // Affichage du résultat de chaque ligne matricielle.
        for (int element : result) {
            System.out.println("Ligne 1: " + result[element]);
        }
    }
}
