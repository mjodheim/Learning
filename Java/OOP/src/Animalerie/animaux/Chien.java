package Animalerie.animaux;

import java.time.LocalDate;

public class Chien extends Animal {

    private static final double PROBABILITE_DECES = 0.01; // 1 %

    private final String couleurCollier;
    private final boolean dresse;
    private final String race;

    public Chien(String nom, double poids, double taille, Sexe sexe, int age, LocalDate dateArrivee,
                 String couleurCollier, boolean dresse, String race) {
        super(nom, poids, taille, sexe, age, dateArrivee);
        this.couleurCollier = couleurCollier;
        this.dresse = dresse;
        this.race = race;
    }

    @Override
    public String crier() {
        return "Wouf !";
    }

    @Override
    public String getEspece() {
        return "Chien";
    }

    // Même départ que le chat (15 puis 9), mais 5 ans par année ensuite.
    @Override
    public int getAgeHumain() {
        int age = getAge();
        if (age <= 0) {
            return 0;
        }
        if (age == 1) {
            return 15;
        }
        return 24 + (age - 2) * 5;
    }

    @Override
    public double getProbabiliteDeDeces() {
        return PROBABILITE_DECES;
    }

    @Override
    protected String detailsSpecifiques() {
        return "Race : %s | Collier : %s | Dressé : %s".formatted(
            race,
            couleurCollier,
            ouiOuNon(dresse)
        );
    }

    public String getCouleurCollier() {
        return couleurCollier;
    }

    public boolean estDresse() {
        return dresse;
    }

    public String getRace() {
        return race;
    }
}
