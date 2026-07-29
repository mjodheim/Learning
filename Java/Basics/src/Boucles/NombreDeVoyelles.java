package Boucles;

import java.text.Normalizer;
import java.util.Scanner;

public final class NombreDeVoyelles {
    private NombreDeVoyelles() {
    }

    public static void run(Scanner scanner) {
        System.out.print("Entrez votre mot ou votre phrase : ");
        String texte = scanner.nextLine().trim().toLowerCase();
        String texteNormalise = Normalizer.normalize(texte, Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");

        int nombreDeVoyelles = 0;
        for (int i = 0; i < texteNormalise.length(); i++) {
            if ("aeiouy".indexOf(texteNormalise.charAt(i)) >= 0) {
                nombreDeVoyelles++;
            }
        }

        System.out.printf("%s contient %d voyelle(s).%n", texte, nombreDeVoyelles);
    }
}
