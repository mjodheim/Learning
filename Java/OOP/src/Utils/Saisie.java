package Utils;

import java.util.Scanner;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;

public final class Saisie {
    private Saisie() {
        // Empêche l'instanciation
    }

    public static int lireEntier(Scanner scanner, String message, IntPredicate condition, String messageErreur) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim();

            try {
                int valeur = Integer.parseInt(saisie);
                if (condition.test(valeur)) {
                    return valeur;
                }
                System.out.println(messageErreur);
            } catch (NumberFormatException e) {
                // Le message de l'appelant sert aussi ici : sans ça, les surcharges
                // qui imposent valeur -> true recevraient un messageErreur inutilisable.
                System.out.println(messageErreur);
            }
        }
    }

    public static int lireEntier(Scanner scanner, String message, String messageErreur) {
        return lireEntier(scanner, message, valeur -> true, messageErreur);
    }

    public static int lireEntier(Scanner scanner, String message, String messageErreur, int min, int max) {
        return lireEntier(
            scanner,
            message,
            valeur -> valeur >= min && valeur <= max,
            messageErreur
        );
    }

    public static double lireReel(Scanner scanner, String message, DoublePredicate condition, String messageErreur) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim().replace(',', '.');

            try {
                double valeur = Double.parseDouble(saisie);
                if (condition.test(valeur)) {
                    return valeur;
                }
                System.out.println(messageErreur);
            } catch (NumberFormatException e) {
                System.out.println(messageErreur);
            }
        }
    }

    public static double lireReel(Scanner scanner, String message, String messageErreur) {
        return lireReel(scanner, message, valeur -> true, messageErreur);
    }
}
