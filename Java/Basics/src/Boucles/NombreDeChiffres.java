package Boucles;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NombreDeChiffres {
    public static void run(Scanner scanner) {
        int nbr, result = 1;
        boolean saisieValide = false;

        do {
            try {
                System.out.print("Entrez un nombre entier positif");
                nbr = scanner.nextInt();
                while (nbr > 1){
                    result ++;
                    nbr /= 10;
                }
                System.out.print("Le nombre " + nbr + " est composé de " + result + "chiffre(s).");
                saisieValide = true;
            } catch(InputMismatchException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }while (!saisieValide);
    }
}
