package SalleCinema;

import SalleCinema.cinema.Salle;

public final class EtatSalle {
    private EtatSalle() {
    }

    public static void run(Salle salle) {
        System.out.printf(
            "%n[%s] %d places vendues sur %d, %d restantes%n",
            salle.getNom(),
            salle.getPlacesVendues(),
            Salle.PLACES_MAX,
            salle.getPlacesRestantes()
        );
        System.out.printf(
            "Recette : %.2f € HT, soit %.2f € TTC%n%n",
            salle.getRecette(),
            Salle.prixTTC(salle.getRecette())
        );
    }
}
