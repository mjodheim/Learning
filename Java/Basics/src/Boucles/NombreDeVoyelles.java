package Boucles;

import java.util.Scanner;

public class NombreDeVoyelles {
    public static void run(Scanner scanner) {
        String mot;
        int nbVoyelles = 0;
        String voyelles = "aeiouy";

        System.out.print("Entrez votre mot: ");
        mot = scanner.nextLine().toLowerCase();

        // Pour chaque voyelle
        for (int i = 0; i < voyelles.length(); i++) {
            // Pour chaque lettre du mot
            for (int j = 0; j < mot.length(); j++) {
                if (mot.charAt(j) == voyelles.charAt(i))
                    nbVoyelles++;
            }
        }

        System.out.print(mot + " contient " + nbVoyelles + "voyelle(s).");
    }
}
