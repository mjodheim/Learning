package models;

import enums.Genre;

public class Dvd extends Media {
    private String realisateur;
    private int dureeMinutes;

    public Dvd(String titre, int anneeSortie, Genre genre, int dureeMinutes, String realisateur) {
        super(titre, anneeSortie, genre);
        setDureeMinutes(dureeMinutes);
        setRealisateur(realisateur);
    }

    // Getters et setters

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }

    public void setDureeMinutes(int dureeMinutes) {
        if (dureeMinutes < 0) {
            throw new IllegalArgumentException(
                    "La durée ne peut pas être négative."
            );
        }
        this.dureeMinutes = dureeMinutes;
    }

    // Overrides

    @Override
    public String typeLibelle() {
        return "DVD";
    }

    @Override
    public int dureeEmpruntJours() {
        return 7;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "media=" + super.toString() +
                ", realisateur='" + realisateur + '\'' +
                ", dureeMinutes=" + dureeMinutes +
                '}';
    }
}
