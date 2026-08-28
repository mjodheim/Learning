package Matrices;

import java.util.Random;

public final class PlusGrandElement {
    private PlusGrandElement() {
    }

    public static void run() {
        Random random = new Random();
        int taille = random.nextInt(1, 11);
        int[][] matrice = new int[taille][taille];

        int maximum = Integer.MIN_VALUE;
        int ligneMaximum = 0;
        int colonneMaximum = 0;

        System.out.printf("Création d'une matrice de taille %d × %d...%n", taille, taille);

        for (int i = 0; i < matrice.length; i++) {
            StringBuilder ligne = new StringBuilder("| ");

            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = random.nextInt(10);
                ligne.append(matrice[i][j]).append(" | ");

                if (matrice[i][j] > maximum) {
                    maximum = matrice[i][j];
                    ligneMaximum = i;
                    colonneMaximum = j;
                }
            }

            System.out.println(ligne);
        }

        System.out.printf("Maximum = %d à la position (%d, %d).%n",
            maximum,
            ligneMaximum,
            colonneMaximum
        );
    }
}
