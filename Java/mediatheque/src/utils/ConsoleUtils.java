package utils;

import java.util.List;
import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleUtils() {}

    public static int lireEntier(String message){
        while(true){
            System.out.print(message);
            String saisie = SCANNER.nextLine();

            try {
                return Integer.parseInt(saisie);
            }  catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre entier valide.");
            }
        }
    }

    public static String lireTexte(String message){
        while(true){
            System.out.print(message);
            String saisie = SCANNER.nextLine();

            if(!saisie.isBlank()) return  saisie;
            System.out.println("Veuillez écrire quelque chose...");
        }
    }

    public static boolean lireOuiNon(String message){
        while(true){
            System.out.print(message + " (o/n)");
            String saisie = SCANNER.nextLine().trim().toLowerCase();
            if(saisie.equals("o") || saisie.equals("oui")) return true;
            if(saisie.equals("n") || saisie.equals("nou")) return false;
            System.out.println("Choix incorrect.");
        }
    }

    public static void afficherTitre(String titre){
        System.out.println();
        System.out.println("==============================");
        System.out.println(titre.toUpperCase());
        System.out.println("==============================");
    }

    public static void afficherListe(List<?> elements){
        if(elements.isEmpty()) {
            System.out.println("Aucun élément à afficher.");
            return;
        }
        for(Object element : elements) {
            System.out.println(element.toString());
        }
    }
}
