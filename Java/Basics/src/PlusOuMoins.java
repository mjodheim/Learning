import java.util.Random;
import java.util.Scanner;

public class PlusOuMoins {
    static void run (Scanner sc){
        int nbUser;
        String message = "";

        // Initialisation random du nbComputer
        Random rand = new Random();
        int nbComputer = rand.nextInt(1,101);

        System.out.println("""
            === L'ordinateur a choisi un nombre entre 1 et 100 ===
                        Tentez de le trouver...
        """);

        do {
            try {
                nbUser = Integer.parseInt(sc.next());
                if (nbUser < 1 || nbUser > 100) throw new NumberFormatException("Veuillez entrer un nombre compris entre 1 et 100");
                message = switch (Integer.compare(nbUser,nbComputer)){
                    case 1 -> "Plus petit";
                    case -1 -> "Plus grand";
                    case 0 -> "Gagné";
                    default -> throw new IllegalStateException("Résultat de comparaison impossible.");
                };
            } catch (NumberFormatException e) {
                System.out.printf("Erreur : %s",e.getMessage());
            } catch (IllegalStateException e){
                System.out.printf("Erreur : %s",e.getMessage());
            }
            System.out.println(message);
        } while (!message.equals("Gagné"));
    }
}
