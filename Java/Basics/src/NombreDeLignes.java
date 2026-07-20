import java.util.Scanner;

public class NombreDeLignes {
    static void run (Scanner sc){
        String saisie;
        int nbLignes, count = 0;

        sc.nextLine(); // Consomme le retour à la ligne restant

        System.out.println("Veuillez entrer un nombre de lignes à afficher (max 20): ");
        saisie = sc.nextLine();

        try {
            nbLignes = Integer.parseInt(saisie);
            if (nbLignes < 1 || nbLignes > 20) throw new NumberFormatException();

            do {
                int nbEtoiles = 0;

                do {
                    System.out.print("*");
                    nbEtoiles++;
                } while (nbEtoiles <= count);

                System.out.println();
                count++;
            } while(count < nbLignes);

        } catch (NumberFormatException e) {
            System.out.printf("Erreur : nombre positif inférieur ou égal à 20 attendu\n", e.getMessage());
        }

        System.out.println("Retour au menu principal...");
    }
}
