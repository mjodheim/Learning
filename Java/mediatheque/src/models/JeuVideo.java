package models;

import enums.Genre;
import enums.Pegi;
import enums.Plateforme;

public class JeuVideo extends Media{
    private Plateforme plateforme;
    private Pegi pegi;

    public JeuVideo(String titre, int anneeSortie, Genre genre, Plateforme plateforme, Pegi pegi) {
        super(titre, anneeSortie, genre);
        setPlateforme(plateforme);
        setPegi(pegi);
    }

    public Plateforme getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(Plateforme plateforme) {
        this.plateforme = plateforme;
    }

    public Pegi getPegi() {
        return pegi;
    }

    public void setPegi(Pegi pegi) {
        this.pegi = pegi;
    }

    // Overrides

    @Override
    public String typeLibelle() {
        return "Jeu vidéo";
    }

    @Override
    public int dureeEmpruntJours() {
        return 14;
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
        return "JeuVideo{" +
                "media=" + super.toString() +
                ", plateforme=" + plateforme +
                ", pegi=" + pegi +
                '}';
    }
}
