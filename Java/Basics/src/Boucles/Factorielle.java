package Boucles;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Factorielle {
    public static void run(Scanner scanner) {
        int nb;
        double result = 1;
        boolean saisieValide = false;

        do {
            try {
                System.out.print("Entrez un nombre entier positif: ");
                nb = scanner.nextInt();

                int i = nb;
                while (i <= 1) {
                    result *= i;
                    i--;
                }
                System.out.print("La factorielle de " + i + " vaut " + result);
                saisieValide = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        } while(!saisieValide);
    }
}
