import models.personnage.heroes.Hero;
import models.personnage.heroes.Humain;
import models.personnage.monsters.Dragonet;
import models.personnage.monsters.Loup;
import models.personnage.monsters.Monstre;
import models.personnage.monsters.Orc;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Humain h = new Humain("John");
        System.out.printf("===== Stats de %s =====%n",h.getNom());
        System.out.printf("Force: %d | Endu: %d | PV: %d%n%n",h.getFor(), h.getEnd(), h.getPV());

        List<Monstre> monstres = List.of(
                new Orc(),
                new Loup(),
                new Dragonet()
        );

        for (Monstre m : monstres) {
            if (!h.estEnVie()) break;

            combat(h,m);
        }
    }

    // Méthodes

    private static void combat (Hero h, Monstre m){
        System.out.printf("%s rencontre un %s%n",h.getNom(),m.getNom());
        System.out.printf("===== Stats de %s =====%n",m.getNom());
        System.out.printf("Force: %d | Endu: %d | PV: %d%n",m.getFor(), m.getEnd(), m.getPV());
        int maxPvO, maxPvH, fullPvH = h.getPV();
        while (h.estEnVie() && m.estEnVie()) {
            System.out.println();
            maxPvO = m.getPV();
            maxPvH = h.getPV();
            h.frappe(m);
            System.out.printf("%s frappe %s et lui enlève %s PV%n",h.getNom(),m.getNom(),maxPvO - m.getPV());
            if (m.estEnVie()) {
                m.frappe(h);
                System.out.printf("%s frappe %s et lui enlève %s PV%n", m.getNom(), h.getNom(), maxPvH - h.getPV());
            }
            System.out.printf("===== PV restants de chacun =====%n");
            System.out.printf("%s: %d | %s: %d%n",h.getNom(), h.getPV(), m.getNom(), m.getPV());
        }
        System.out.println();
        if (h.estEnVie()) {
            h.collecter(m);
            System.out.printf(" %s a battu %s et a récupéré %d or et %d cuirs.",
                    h.getNom(), m.getNom(), m.getOr(), m.getCuir());
            System.out.printf("%s se soigne et récupère tous ses pv !", h.getNom());
            h.setPV(fullPvH);
        }
        else System.out.println(m.getNom() + " a battu " + h.getNom() + "!");
    }
}
