package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Emprunt{
    private final Media media;
    private final Membre membre;
    private final LocalDate dateEmprunt;
    private final LocalDate dateRetourPrevue;
    private LocalDate dateRetourReelle;

    public Emprunt(Media media, Membre membre) {
        if(media == null) {
            throw new NullPointerException(
                    "Le média ne peut pas être nul."
            );
        }
        this.media = media;
        if(membre == null) {
            throw new NullPointerException(
                    "Le membre ne peut pas être nul."
            );
        }
        this.membre = membre;
        this.dateEmprunt = LocalDate.now();
        this.dateRetourPrevue = dateEmprunt.plusDays(media.dureeEmpruntJours());
        this.dateRetourReelle = null;

        // Il faut aussi marquer le média comme emprunté
        media.marquerEmprunte();
    }

    // Getters

    public Media getMedia() {
        return media;
    }

    public Membre getMembre() {
        return membre;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public LocalDate getDateRetourReelle() {
        return dateRetourReelle;
    }

    // Méthodes

    public boolean estEnCours(){
        return dateRetourReelle == null;
    }

    public boolean estEnRetard(){
        return estEnCours() && dateRetourPrevue.isBefore(LocalDate.now());
    }

    public long joursDeRetard(){
        if(!estEnRetard()){
            return 0;
        }
        return ChronoUnit.DAYS.between(dateRetourPrevue, LocalDate.now());
    }

    public long cloturer(){
        long retard = joursDeRetard();

        this.dateRetourReelle = LocalDate.now();

        // Libération du média
        media.marquerDisponible();

        return retard;
    }

    // Overrides

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Emprunt emprunt = (Emprunt) o;
        return Objects.equals(media, emprunt.media) && Objects.equals(membre, emprunt.membre) && Objects.equals(dateEmprunt, emprunt.dateEmprunt) && Objects.equals(dateRetourPrevue, emprunt.dateRetourPrevue) && Objects.equals(dateRetourReelle, emprunt.dateRetourReelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(media, membre, dateEmprunt, dateRetourPrevue, dateRetourReelle);
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "media=" + media +
                ", membre=" + membre +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetourPrevue=" + dateRetourPrevue +
                ", dateRetourReelle=" + dateRetourReelle +
                '}';
    }
}
