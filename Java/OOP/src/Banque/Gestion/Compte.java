package Banque.Gestion;

import Banque.Personne;
import Banque.Utils.Utils;

import java.util.Objects;

// Ce que tous les comptes ont en commun.

public abstract class Compte {

    // Identité du compte : fixée à l'ouverture, elle ne change plus.
    private final String numeroDeCompte;
    private final Personne titulaire;

    // En read only, les opérations se font ici
    private double SOLDE;

    protected Compte(String numeroDeCompte, Personne titulaire, double SOLDE) {
        if (titulaire == null) {
            throw new IllegalArgumentException("Un compte a toujours un titulaire.");
        }
        if (SOLDE < 0) {
            throw new IllegalArgumentException("Le solde d'ouverture ne peut pas être négatif.");
        }

        this.numeroDeCompte = numeroDeCompte;
        this.titulaire = titulaire;
        this.SOLDE = SOLDE;
    }

    public abstract String getTypeDeCompte();


    public abstract double getMontantRetirableMax();


    public abstract double getTauxInterets();


    public abstract double calculerInterets();


    protected abstract String detailsSpecifiques();


    public void deposer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Un dépôt doit être strictement positif.");
        }
        SOLDE += montant;
    }

    public boolean retirer(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Un retrait doit être strictement positif.");
        }
        if (montant > getMontantRetirableMax()) {
            return false;
        }

        SOLDE -= montant;
        return true;
    }

    public final double appliquerInterets() {
        double interets = calculerInterets();
        SOLDE += interets;
        return interets;
    }

    // Le corps de l'extrait : Utils.imprimerExtrait se charge du cadre autour.
    public final String decrire() {
        return """
            Compte : %s (%s)
            Titulaire : %s
            Solde : %s
            Retrait maximum : %s
            %s""".formatted(
            numeroDeCompte,
            getTypeDeCompte(),
            titulaire.getNomComplet(),
            Utils.formaterMontant(SOLDE),
            Utils.formaterMontant(getMontantRetirableMax()),
            detailsSpecifiques()
        );
    }

    // Version courte, pour les listes de choix des menus.
    @Override
    public String toString() {
        return "%s %s — solde %s".formatted(
            getTypeDeCompte(),
            numeroDeCompte,
            Utils.formaterMontant(SOLDE)
        );
    }

    public String getNumeroDeCompte() {
        return numeroDeCompte;
    }

    public Personne getTitulaire() {
        return titulaire;
    }

    public double getSolde() {
        return SOLDE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return Objects.equals(numeroDeCompte, compte.numeroDeCompte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDeCompte);
    }
}
