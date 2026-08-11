import SalleCinema.SalleCinema;
import Utils.Saisie;

void main() {

    // On ne crée qu'un seul scanner qu'on passe potentiellement aux autres sous-programmes et qu'on ferme à la fin
    Scanner scanner = new Scanner(System.in);

    int choix;

    do {
        IO.print("""
            === Choix de l'exercice à lancer ===
            1. Salle de cinéma (modificateurs d'accès et méthodes)
            0. Quitter
            """);

        choix = Saisie.lireEntier(
            scanner,
            "",
            "Choix invalide, veuillez ré-essayer...",
            0,
            1
        );

        switch (choix) {
            case 1 -> SalleCinema.run(scanner);
            default -> IO.println("Merci, à bientôt !");
        }
    } while (choix != 0);


    // On ferme le scanner
    scanner.close();
}
