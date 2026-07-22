package MiniApplications;

import java.util.Scanner;

public class SystemeDeConnexion{
    public static void run (Scanner sc){
        String ref = "Test123!", pwd, sortie;
        int count = 0;

        sc.nextLine(); // Consomme le retour à la ligne restant

        do {
            IO.print("=== Veuillez entrer le bon mot de passe ===");
            pwd = sc.nextLine();

            if (pwd.equals(ref)) break;

            IO.print("Mot de passe invalide, veuillez recommencer...");
            count++;
        } while(count < 3);

        sortie = (count != 3) ? "Mot de passe correct." : "Vous avez échoué 3 fois, compte bloqué.";
        IO.print(sortie);
    }
}
