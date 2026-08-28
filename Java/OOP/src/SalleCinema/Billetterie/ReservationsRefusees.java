package SalleCinema.Billetterie;

import SalleCinema.EtatSalle;
import SalleCinema.cinema.Salle;

public final class ReservationsRefusees {
    private ReservationsRefusees() {
    }

    public static void run(Salle salle) {
        System.out.println("Les trois cas que le guichet refuse :");

        // Chaque appel est arrêté par une garde différente et ressort
        // immédiatement en false, sans jamais toucher à l'état de la salle.
        System.out.printf("0 place demandée          -> %b%n", salle.reserver(0));
        System.out.printf("réduction de 150 %%        -> %b%n", salle.reserver(2, 1.5));
        System.out.printf("500 places d'un coup      -> %b%n", salle.reserver(500));

        System.out.println("\nAucune de ces tentatives n'a modifié la salle :");
        EtatSalle.run(salle);
    }
}
