import java.util.Scanner;

public class TableauDixEntiers {
    static void run (Scanner sc){
        int exp = 2;
        int tab[] = new int [10];
        String result;

        // Initialisation du tableau avec des valeurs exposantes de exp
        for (int i=0; i< tab.length; i++){
            tab[i] = (int) Math.pow(exp,i+1);
        }
        // Affichage du tableau, avec une sortie formatée
        result = "Tableau : ";
        for (int i=0; i< tab.length; i++){
            result += (i == 0) ? ("| " + tab[i] + " | ") : (tab[i] + " | ");
        }
        System.out.println(result);
    }
}
