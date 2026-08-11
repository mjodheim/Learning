package SalleCinema.Direction;

import SalleCinema.EtatSalle;
import SalleCinema.cinema.Salle;
import SalleCinema.cinema.premium.SalleVIP;

public final class RapportDesSalles {
    private RapportDesSalles() {
    }

    public static void run(Salle salle, SalleVIP vip) {
        System.out.println("Rapport du jour :");
        EtatSalle.run(salle);
        EtatSalle.run(vip);

        // La VIP est une Salle : elle passe partout où une Salle est attendue.
        double totalHT = salle.getRecette() + vip.getRecette();
        System.out.printf(
            "Total des deux salles : %d places vendues, %.2f € HT, %.2f € TTC%n%n",
            salle.getPlacesVendues() + vip.getPlacesVendues(),
            totalHT,
            Salle.prixTTC(totalHT)
        );
    }
}
