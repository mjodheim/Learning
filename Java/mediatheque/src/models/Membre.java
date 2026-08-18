package models;

import java.time.LocalDate;
import java.util.Objects;

public class Membre{
    private static int nextId = 1;

    private final int id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateInscription;

    public Membre(String nom, String prenom, String email, LocalDate dateInscription) {
        this.id = nextId++;
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setDateInscription(dateInscription);
    }

    // Getters et setters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom ne peut pas être nul ou vide."
            );
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null  || prenom.isBlank()) {
            throw new IllegalArgumentException(
                    "Le prénom ne peut pas être nul ou vide."
            );
        }
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "L'email ne peut pas être nul ou vide."
            );
        }
        this.email = email;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        if (dateInscription == null) {
            throw new IllegalArgumentException(
                    "La date ne peut pas être nulle."
            );
        }
        if (dateInscription.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "La date ne peut pas être dans le futur."
            );
        }
        this.dateInscription = dateInscription;
    }

    // Overrides

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Membre membre = (Membre) o;
        return Objects.equals(email, membre.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "Membre{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateInscription=" + dateInscription +
                '}';
    }
}
