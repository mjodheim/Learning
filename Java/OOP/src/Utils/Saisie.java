package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;

public final class Saisie {

    // Accepte aussi bien 4/7/2026 que 04/07/2026.
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("d/M/yyyy");

    private Saisie() {}

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

    public static String lireTexte(Scanner scanner, String message, String messageErreur) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim();

            if (!saisie.isEmpty()) {
                return saisie;
            }
            System.out.println(messageErreur);
        }
    }

    public static boolean lireOuiNon(Scanner scanner, String message, String messageErreur) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim().toLowerCase();

            switch (saisie) {
                case "o", "oui" -> {
                    return true;
                }
                case "n", "non" -> {
                    return false;
                }
                default -> System.out.println(messageErreur);
            }
        }
    }

    // Une saisie vide renvoie la valeur par défaut proposée par l'appelant.
    public static LocalDate lireDate(Scanner scanner, String message, String messageErreur, LocalDate valeurParDefaut) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim();

            if (saisie.isEmpty() && valeurParDefaut != null) {
                return valeurParDefaut;
            }

            try {
                return LocalDate.parse(saisie, FORMAT_DATE);
            } catch (DateTimeParseException e) {
                System.out.println(messageErreur);
            }
        }
    }

    // Sans valeur par défaut, une date valide est obligatoire.
    public static LocalDate lireDate(Scanner scanner, String message, String messageErreur) {
        return lireDate(scanner, message, messageErreur, null);
    }

    public static <T extends Enum<T>> T lireEnumeration(Scanner scanner, String titre, T[] valeurs, String messageErreur) {
        System.out.println(titre);
        for (int i = 0; i < valeurs.length; i++) {
            System.out.printf("%d. %s%n", i + 1, valeurs[i]);
        }

        int choix = lireEntier(scanner, "", messageErreur, 1, valeurs.length);
        return valeurs[choix - 1];
    }
}
