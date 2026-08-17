import Animalerie.Animalerie;
import SalleCinema.SalleCinema;
import SalleCinema.cinema.Salle;
import Banque.Banque;
import Utils.Saisie;
import Utils.IO;
import java.util.Scanner;

void main() {

    // On ne crée qu'un seul scanner qu'on passe potentiellement aux autres sous-programmes et qu'on ferme à la fin
    Scanner scanner = new Scanner(System.in);

    int choix;

    do {
        IO.print("""
            === Choix de l'exercice à lancer ===
            1. Salle de cinéma (modificateurs d'accès et méthodes)
            2. Animalerie (héritage, classe abstraite et polymorphisme)
            3. Banque
            0. Quitter
            """);

        choix = Saisie.lireEntier(
            scanner,
            "",
            "Choix invalide, veuillez ré-essayer...",
            0,
            3
        );

        switch (choix) {
            case 1 -> SalleCinema.run(scanner);
            case 2 -> Animalerie.run(scanner);
            case 3 -> Banque.run(scanner);
            default -> IO.println("Merci, à bientôt !");
        }
    } while (choix != 0);


    // On ferme le scanner
    scanner.close();
}

// Auto-contrôle de l'énoncé.

void autoControle(Salle salle) {
    // int a = salle.placesVendues;  // private
    // int b = salle.codeNettoyage;  // default, et Main est hors du package cinema
    // int c = salle.niveauSonore;   // protected, et Main n'hérite pas de Salle

    // Seul le contrat public passe :
    IO.println(salle.getNom() + " : " + Salle.PLACES_MAX + " places maximum.");
}
