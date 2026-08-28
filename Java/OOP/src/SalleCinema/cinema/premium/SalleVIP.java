package SalleCinema.cinema.premium;

import SalleCinema.cinema.Salle;

public class SalleVIP extends Salle {
    private final int nombreFauteuils;

    public SalleVIP(String nom, int niveauSonore, int codeNettoyage, int nombreFauteuils) {
        super(nom, niveauSonore, codeNettoyage);
        this.nombreFauteuils = nombreFauteuils;
    }

    public void reglerAmbiance(int nouveauNiveau) {
        // Auto-contrôle de l'énoncé, côté sous-classe.
        // niveauSonore est protected : accessible, car SalleVIP hérite de Salle.
        niveauSonore = nouveauNiveau;

        // codeNettoyage est en default : SalleVIP a beau hériter de Salle, elle est
        // dans un autre package. Décommentez : le projet doit refuser de compiler.
        // int code = codeNettoyage;

        System.out.printf(
            "Salle VIP %s : %d fauteuils, niveau sonore réglé à %d dB.%n",
            getNom(), nombreFauteuils, niveauSonore
        );
    }
}
