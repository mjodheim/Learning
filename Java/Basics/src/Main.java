import Boucles.Boucles;
import Calculs.Calculs;
import MiniApplications.MiniApplications;
import Tableaux.Tableaux;
import Utils.Saisie;

void main() {

    // On ne crée qu'un seul scanner qu'on passe potentiellement aux autres sous-programmes et qu'on ferme à la fin
    Scanner scanner = new Scanner(System.in);

    int choix;

    do {
        IO.print("""
            === Choix de l'exercice à lancer par thème ===
            1. Boucles
            2. Calculs
            3. Mini applications
            4. Tableaux
            0. Quitter
            """);

        choix = Saisie.lireEntier(
            scanner,
            "",
            "Choix invalide, veuillez ré-essayer...",
            0,
            4
        );

        switch (choix) {
            case 1 -> Boucles.run(scanner);
            case 2 -> Calculs.run(scanner);
            case 3 -> MiniApplications.run(scanner);
            case 4 -> Tableaux.run(scanner);
            default -> IO.println("Merci, à bientôt !");
        }
    } while (choix != 0);


    // On ferme le scanner
    scanner.close();
}