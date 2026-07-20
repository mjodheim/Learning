import java.util.InputMismatchException;
import java.util.Scanner;

public class Exposant {
    static void run (Scanner sc){
        int nbr = 0, exposant = 0, count, signeNbr = 0, result, signeResult = 1;
        float resultExposantNegatif;
        boolean saisieValide = false;

        System.out.println("Veuillez entrer un nombre et un exposant :");
        do {
            System.out.print("Nombre : ");
            try {
                nbr = Integer.parseInt(sc.next());
                signeNbr = switch (Integer.compare(nbr, 0)){
                    case 1 -> 1; // positif
                    case -1 -> -1; // negatif
                    default -> throw new InputMismatchException("Résultat de comparaison impossible.");
                };
                nbr = Math.abs(nbr); // On ne garde que la valeur absolue
                saisieValide = true;
            } catch (NumberFormatException e) {
                System.err.printf("Erreur %s Veuillez entrer un nombre entier valide.", e.getMessage());
            } catch (InputMismatchException e) {
                System.err.printf("Erreur = %s", e.getMessage());
            }
        } while(!saisieValide);

        // cas du nbr à 0
        if (nbr == 0) {
            System.out.println("""
                Peu importe la puissance, le résultat vaudra toujours 0
                Retour au menu principal...
                """);
            return; // on retourne directement au menu principal
        }
        saisieValide = false;

        do {
            System.out.print("Exposant : ");
            try {
                exposant = Integer.parseInt(sc.next());
                saisieValide = true;
            } catch (NumberFormatException e) {
                System.err.printf("Erreur %s Veuillez entrer un nombre entier valide.", e.getMessage());
            }
        } while(!saisieValide);

        // Cas de l'expostant à 0
        if (exposant == 0) {
            System.out.println("""
                Peu importe le nombre, le résultat vaudra toujours 1
                Retour au menu principal...
                """);
            return; // on retourne directement au menu principal
        }

        // Boucle pour le calcul (sans utiliser de méthode donc)
        result = nbr;
        count = 1;

        do {
            result *= nbr;
            count ++;
        } while (count < Math.abs(exposant));

        // Calcul du signe
        if (signeNbr == -1 && exposant % 2 != 0 ){
            signeResult = -1;
        }

        // Si exposant positif
        if (exposant > 0) System.out.printf("Le resultat de %d élevé à la puissance %d vaut %d\n",nbr, exposant, signeResult * result);
        // Si exposant négatif
        resultExposantNegatif = signeResult * (1 / (Float.parseFloat(result + "")));
        if(exposant < 0) System.out.printf("Le resultat de %d élevé à la puissance %d vaut 1 / %d : %f\n",nbr, exposant, signeResult * result, resultExposantNegatif);

        System.out.println("Retour au menu principal...");
    }
}
