package Tableaux;

import Utils.Saisie;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public final class DistributeurDeBoissons {
    private DistributeurDeBoissons() {
    }

    public static void run(Scanner scanner) {
        Map<String, Integer> stock = new LinkedHashMap<>();
        stock.put("Coca", 10);
        stock.put("Sprite", 8);
        stock.put("Fanta", 5);

        int choix;
        do {
            System.out.print("""
                === Distributeur de boissons ===
                1. Coca
                2. Sprite
                3. Fanta
                0. Quitter
                """);

            choix = Saisie.lireEntier(
                scanner,
                "Votre choix : ",
                "Choix invalide.",
                0,
                3
            );

            if (choix == 0) {
                System.out.println("Retour au menu précédent...");
                continue;
            }

            if (stock.values().stream().allMatch(quantite -> quantite == 0)) {
                System.out.println("Le distributeur est vide, veuillez le réapprovisionner.");
                break;
            }

            String boisson = switch (choix) {
                case 1 -> "Coca";
                case 2 -> "Sprite";
                case 3 -> "Fanta";
                default -> throw new IllegalStateException("Choix inattendu : " + choix);
            };

            int quantite = stock.get(boisson);
            if (quantite == 0) {
                System.out.printf("Il n'y a plus de %s. Veuillez faire un autre choix.%n", boisson);
            } else {
                stock.put(boisson, quantite - 1);
                System.out.printf("Vous recevez un %s. Stock restant : %d.%n", boisson, quantite - 1);
            }
        } while (choix != 0);
    }
}
