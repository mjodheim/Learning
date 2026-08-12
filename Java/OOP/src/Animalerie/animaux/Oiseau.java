package Animalerie.animaux;

import java.time.LocalDate;

public class Oiseau extends Animal {

    private static final double PROBABILITE_DECES = 0.03; // 3 %

    private final String couleur;
    private final Logement logement;

    public Oiseau(String nom, double poids, double taille, Sexe sexe, int age, LocalDate dateArrivee,
                  String couleur, Logement logement) {
        super(nom, poids, taille, sexe, age, dateArrivee);
        this.couleur = couleur;
        this.logement = logement;
    }

    @Override
    public String crier() {
        return "Cui-cui !";
    }

    @Override
    public String getEspece() {
        return "Oiseau";
    }

    // Très variable d'une espèce d'oiseau à l'autre : on retient une approximation simple.
    @Override
    public int getAgeHumain() {
        return Math.max(getAge(), 0) * 6;
    }

    @Override
    public double getProbabiliteDeDeces() {
        return PROBABILITE_DECES;
    }

    @Override
    protected String detailsSpecifiques() {
        return "Couleur : %s | Logement : %s".formatted(couleur, logement);
    }

    public String getCouleur() {
        return couleur;
    }

    public Logement getLogement() {
        return logement;
    }

    public boolean vitEnVoliere() {
        return logement == Logement.VOLIERE;
    }
}
