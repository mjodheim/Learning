package Banque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Personne {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String nom;
    private final String prenom;
    private final LocalDate dateNaissance;

    public Personne(String nom, String prenom, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getNomComplet() {
        return "%s %s".formatted(prenom, nom);
    }

    @Override
    public String toString() {
        return "%s (né(e) le %s)".formatted(getNomComplet(), dateNaissance.format(FORMAT_DATE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(nom, personne.nom)
            && Objects.equals(prenom, personne.prenom)
            && Objects.equals(dateNaissance, personne.dateNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, dateNaissance);
    }
}
