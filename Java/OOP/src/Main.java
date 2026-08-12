import Animalerie.Animalerie;
import SalleCinema.SalleCinema;
import SalleCinema.cinema.Salle;
import Utils.Saisie;

void main() {

    // On ne crée qu'un seul scanner qu'on passe potentiellement aux autres sous-programmes et qu'on ferme à la fin
    Scanner scanner = new Scanner(System.in);

    int choix;

    do {
        IO.print("""
            === Choix de l'exercice à lancer ===
            1. Salle de cinéma (modificateurs d'accès et méthodes)
            2. Animalerie (héritage, classe abstraite et polymorphisme)
            0. Quitter
            """);

        choix = Saisie.lireEntier(
            scanner,
            "",
            "Choix invalide, veuillez ré-essayer...",
            0,
            2
        );

        switch (choix) {
            case 1 -> SalleCinema.run(scanner);
            case 2 -> Animalerie.run(scanner);
            default -> IO.println("Merci, à bientôt !");
        }
    } while (choix != 0);


    // On ferme le scanner
    scanner.close();
}

/* Auto-contrôle de l'énoncé.
 * Cette méthode n'est jamais appelée : elle ne sert qu'à vérifier la visibilité
 * depuis Main, qui n'est ni dans le package cinema, ni une sous-classe de Salle.
 * Décommentez n'importe laquelle des trois lignes : le projet doit refuser de compiler.
 */
void autoControle(Salle salle) {
    // int a = salle.placesVendues;  // private
    // int b = salle.codeNettoyage;  // default, et Main est hors du package cinema
    // int c = salle.niveauSonore;   // protected, et Main n'hérite pas de Salle

    // Seul le contrat public passe :
    IO.println(salle.getNom() + " : " + Salle.PLACES_MAX + " places maximum.");
}
