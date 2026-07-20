import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DistributeurDeBoissons {
    static void run (Scanner sc){
        int choix;
        String boisson = "";

        Map <String, Integer> stock = new HashMap<>();
        stock.put("Coca", 10);
        stock.put("Sprite", 8);
        stock.put("Fanta", 5);

        do {
            System.out.println("""
                === Distributeur de boissons ===
                1. Coca
                2. Sprite
                3. Fanta
                0. Quitter
                """);
            choix = sc.nextInt();

            switch (choix) {
                case 1: boisson = "Coca";
                    break;
                case 2: boisson = "Sprite";
                    break;
                case 3: boisson = "Fanta";
                    break;
                case 0: System.out.println("Retour au menu principal...");
                    break;
                default: boisson = "";
            }
            if (boisson == "") break;

            // Cas du distributeur vide
            if (stock.values().stream().allMatch(x -> x == 0)) {
                System.out.println("Le distributeur est vide, veuillez réapprovisionner");
                break;
            }

            if (stock.get(boisson) == 0) {
                System.out.printf("Il n'y a plus de %s. Veuillez faire un autre choix...\n", boisson);
            } else {
                System.out.printf("--- Vous recevez un %s \n", boisson);
                stock.put(boisson, stock.get(boisson) - 1);
            }
        } while(choix != 0);
    }
}
