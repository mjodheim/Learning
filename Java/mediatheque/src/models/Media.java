package models;

import enums.Genre;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Media {
    // static: partagé entre tous les médias
    private static int nextId = 1;

    // final: ne doit jamais changer une fois attribué
    private final int id;
    private String titre;
    private int anneeSortie;
    private Genre genre;
    private boolean disponible;

    public Media(String titre, int anneeSortie, Genre genre) {
        this.id = nextId++;
        setTitre(titre);
        setAnneeSortie(anneeSortie);
        setGenre(genre);
        this.disponible = true;
    }

    // Getters et setters

    public final int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.isEmpty()) {
            throw new IllegalArgumentException(
                    "Le titre ne peut pas être nul ou vide."
            );
        }
        this.titre = titre;
    }

    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(int anneeSortie) {
        // Récupération de l'année courante
        int anneeCourante = LocalDate.now().getYear();

        if (anneeSortie < 1900 || anneeSortie > anneeCourante) {
            throw new IllegalArgumentException(
                    "L'année doit être comprise entre 1900 et " +
                            anneeCourante + "."
            );
        }
        this.anneeSortie = anneeSortie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException(
                    "Le genre ne peut pas être nul."
            );
        }
        this.genre = genre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // Méthodes retreintes au package

    void marquerEmprunte() {
        this.disponible = false;
    }

    void marquerDisponible() {
        this.disponible = true;
    }

    // Méthodes abstract

    public abstract int dureeEmpruntJours();

    public abstract String typeLibelle();

    // Overrides

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", anneeSortie=" + anneeSortie +
                ", genre=" + genre +
                ", disponible=" + disponible +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return id == media.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
