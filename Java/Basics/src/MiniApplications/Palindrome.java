package MiniApplications;

import java.text.Normalizer;
import java.util.Scanner;

public final class Palindrome {
    private Palindrome() {
    }

    public static void run(Scanner scanner) {
        System.out.print("Entrez un mot ou une phrase : ");
        String saisie = scanner.nextLine().trim();

        /* Nettoyage du texte avant la vérification du palindrome :
        * 1. passage en minuscules pour ignorer la casse
        * 2. normalisation Unicode NFD pour séparer les lettres de leurs accents
        * 3. suppression des accents avec \p{M}
        * 4. suppression des espaces, de la ponctuation et des caractères spéciaux
        */
        String texte = Normalizer.normalize(saisie.toLowerCase(), Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "")
            .replaceAll("[^a-z0-9]", "");

        if (texte.isEmpty()) {
            System.out.println("Aucun caractère à analyser.");
            return;
        }

        boolean palindrome = true;
        for (int i = 0; i < texte.length() / 2; i++) {
            if (texte.charAt(i) != texte.charAt(texte.length() - i - 1)) {
                palindrome = false;
                break;
            }
        }

        System.out.printf("%s %s un palindrome.%n", saisie, palindrome ? "est" : "n'est pas");
    }
}
