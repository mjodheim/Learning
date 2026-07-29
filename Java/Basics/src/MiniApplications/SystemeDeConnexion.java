package MiniApplications;

import java.util.Scanner;

public final class SystemeDeConnexion {
    private static final String MOT_DE_PASSE = "Test123!";
    private static final int NOMBRE_MAX_ESSAIS = 3;

    private SystemeDeConnexion() {
    }

    public static void run(Scanner scanner) {
        for (int essai = 1; essai <= NOMBRE_MAX_ESSAIS; essai++) {
            System.out.print("Veuillez entrer le mot de passe : ");
            String motDePasse = scanner.nextLine();

            if (MOT_DE_PASSE.equals(motDePasse)) {
                System.out.println("Mot de passe correct.");
                return;
            }

            int essaisRestants = NOMBRE_MAX_ESSAIS - essai;
            if (essaisRestants > 0) {
                System.out.printf("Mot de passe invalide. Il reste %d essai(s).%n", essaisRestants);
            }
        }

        System.out.println("Vous avez échoué 3 fois, compte bloqué.");
    }
}
