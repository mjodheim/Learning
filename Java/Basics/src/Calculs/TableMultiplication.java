package Calculs;

import java.util.Scanner;

public class TableMultiplication {
    public static void run (Scanner sc) {
        int choix;

        do {
            IO.print("""
                === Menu des tables de multiplication ===
                1. Table au choix
                2. Toutes les tables de 1 à 9
                0. Quitter
                """);
            choix = sc.nextInt();
            switch (choix) {
                case 1: choix(sc);
                case 2: simple();
                case 0: IO.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }

    public static void choix (Scanner sc){
        int choix;
        IO.print("Quelle table veux-tu afficher ?");
        choix = sc.nextInt();
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d * %d = %d \n", i, choix, (i * choix));
        }
    }

    public static void simple (){
        IO.println("--- Tables de multiplication de 1 à 9 ---");
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%d * %d = %d \n", i, j, (i * j));
            }
        }
    }
}
