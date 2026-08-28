package SalleCinema.Direction;

import SalleCinema.cinema.Salle;

public final class Habilitations {
    private Habilitations() {
    }

    public static void run(Salle salle) {
        System.out.println("""
            Qui a le droit de lire quoi dans une Salle ?

            Depuis ce package (hors de cinema et de cinema.premium) :
              placesVendues   REFUSE  : private
              codeNettoyage   REFUSE  : default
              niveauSonore    REFUSE  : protected sans lien d'héritage
            Depuis AgentDEntretien (même package que Salle) :
              codeNettoyage   ACCEPTE : default
            Depuis SalleVIP (autre package, mais sous-classe) :
              niveauSonore    ACCEPTE : protected
              codeNettoyage   REFUSE  : default

            Les lignes correspondantes sont commentées dans le code :
            décommentez-les pour vérifier que le compilateur les refuse bien.
            """);

        // int a = salle.placesVendues;  // ne compile pas
        // int b = salle.codeNettoyage;  // ne compile pas
        // int c = salle.niveauSonore;   // ne compile pas

        // Seul le contrat public passe :
        System.out.printf(
            "Contrat public accessible d'ici : %s, %d places max, %d restantes.%n%n",
            salle.getNom(),
            Salle.PLACES_MAX,
            salle.getPlacesRestantes()
        );
    }
}
