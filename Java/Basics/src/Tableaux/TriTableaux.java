package Tableaux;

import java.util.Random;

public class TriTableaux {
    public static void run(){
        Random random = new Random();

        int j = 0, curseur, valeur;

        int[] tab1 = new int[random.nextInt(1,6)];
        int[] tab2 = new int[random.nextInt(1,6)];
        int[] tab3 = new int[tab1.length +  tab2.length];

        StringBuilder result = new StringBuilder();

        // Remplissage des deux premiers tableaux
        for (int i = 0; i < tab1.length; i++) {
            tab1[i] = random.nextInt(101);
        }
        for (int i = 0; i < tab2.length; i++) {
            tab2[i] = random.nextInt(101);
        }

        // Remplissage du troisième tableau et tri à la volée
        for (int i = 0; i < tab3.length; i++){
            if (i < tab1.length){
                valeur = tab1[i];
            }
            else {
                valeur = tab2[j];
                j++;
            }

            curseur = i; // on repositionne le curseur au bon endroit

            while (curseur > 0 && valeur < tab3[curseur-1]){
                tab3[curseur] = tab3[curseur-1];
                curseur--;
            }

            tab3[curseur] = valeur;
        }

        // Affichage du tableau trié
        for (int element : tab3){
            result.append(element).append(" | ");
        }

        System.out.println(result);
    }
}
