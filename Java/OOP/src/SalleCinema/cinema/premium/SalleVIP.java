package SalleCinema.cinema.premium;

import SalleCinema.cinema.Salle;

public class SalleVIP extends Salle {
    private final int nombreFauteuils;

    public SalleVIP(String nom, int niveauSonore, int codeNettoyage, int nombreFauteuils) {
        super(nom, niveauSonore, codeNettoyage);
        this.nombreFauteuils = nombreFauteuils;
    }

    public void reglerAmbiance(int nouveauNiveau) {
        // niveauSonore est protected : accessible, car SalleVIP hérite de Salle.
        niveauSonore = nouveauNiveau;

        System.out.printf(
            "Salle VIP %s : %d fauteuils, niveau sonore réglé à %d dB.%n",
            getNom(), nombreFauteuils, niveauSonore
        );
    }
}
