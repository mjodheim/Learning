package Tableaux;

public class TableauDixEntiers {
    public static void run (){
        int exp = 2;
        int[] tab = new int [10];
        StringBuilder result = new StringBuilder();

        // Initialisation du tableau avec des valeurs exposantes
        for (int i=0; i< tab.length; i++){
            tab[i] = (int) Math.pow(exp,i+1);
        }
        // Affichage du tableau, avec une sortie formatée
        result.append("Tableau : ");
        for (int i=0; i< tab.length; i++){
            result.append((i == 0) ? ("| " + tab[i] + " | ") : (tab[i] + " | "));
        }
        IO.print(result);
    }
}
