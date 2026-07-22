package MiniApplications;

import Utils.Saisie;

import java.util.Random;
import java.util.Scanner;

public class PlusOuMoins {
    public static void run (Scanner sc){
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
            nbUser = Saisie.lireEntier(
                sc,
                "",
                "Veuillez entrer un nombre compris entre 1 et 100",
                1,
                100
            );
            message = switch (Integer.compare(nbUser,nbComputer)){
                case 1 -> "Plus petit";
                case -1 -> "Plus grand";
                default -> "Gagné";
            };
            System.out.println(message);
        } while (!message.equals("Gagné"));
    }
}
