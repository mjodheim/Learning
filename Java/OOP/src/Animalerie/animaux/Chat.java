package Animalerie.animaux;

import java.time.LocalDate;

public class Chat extends Animal {

    private static final double PROBABILITE_DECES = 0.005; // 0,5 %

    private final Caractere caractere;
    private final boolean griffesCoupees;
    private final boolean poilLong;

    public Chat(String nom, double poids, double taille, Sexe sexe, int age, LocalDate dateArrivee,
                Caractere caractere, boolean griffesCoupees, boolean poilLong) {
        // Les champs communs partent d'abord chez Animal.
        super(nom, poids, taille, sexe, age, dateArrivee);
        this.caractere = caractere;
        this.griffesCoupees = griffesCoupees;
        this.poilLong = poilLong;
    }

    @Override
    public String crier() {
        return "Miaou !";
    }

    @Override
    public String getEspece() {
        return "Chat";
    }

    // Barème courant : 15 ans la première année, 9 de plus la deuxième, puis 4 par année.
    @Override
    public int getAgeHumain() {
        int age = getAge();
        if (age <= 0) {
            return 0;
        }
        if (age == 1) {
            return 15;
        }
        return 24 + (age - 2) * 4;
    }

    @Override
    public double getProbabiliteDeDeces() {
        return PROBABILITE_DECES;
    }

    @Override
    protected String detailsSpecifiques() {
        return "Caractère : %s | Griffes coupées : %s | Poil long : %s".formatted(
            caractere,
            ouiOuNon(griffesCoupees),
            ouiOuNon(poilLong)
        );
    }

    public Caractere getCaractere() {
        return caractere;
    }

    public boolean aLesGriffesCoupees() {
        return griffesCoupees;
    }

    public boolean estAPoilLong() {
        return poilLong;
    }
}
