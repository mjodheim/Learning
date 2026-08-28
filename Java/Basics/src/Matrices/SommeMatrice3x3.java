package Matrices;

import java.util.Random;

public final class SommeMatrice3x3 {
    private SommeMatrice3x3() {
    }

    public static void run() {
        int[][] matrice = new int[3][3];
        int[] sommes = new int[3];
        Random random = new Random();

        for (int i = 0; i < matrice.length; i++) {
            StringBuilder ligne = new StringBuilder("| ");

            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = random.nextInt(10);
                sommes[i] += matrice[i][j];
                ligne.append(matrice[i][j]).append(" | ");
            }

            System.out.println(ligne);
        }

        for (int i = 0; i < sommes.length; i++) {
            System.out.printf("Somme de la ligne %d : %d%n", i + 1, sommes[i]);
        }
    }
}
