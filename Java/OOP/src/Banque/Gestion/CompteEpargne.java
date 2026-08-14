package Banque.Gestion;

import Banque.Personne;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CompteEpargne extends Compte {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final double TAUX_INTERETS = 0.045; // 4,5 %

    private LocalDate dateDernierRetrait;

    // Un compte épargne ouvert sans versement démarre à zéro.
    public CompteEpargne(String numeroDeCompte, Personne titulaire) {
        this(numeroDeCompte, titulaire, 0, null);
    }

    // La date est nulle à la création du compte épargne.
    public CompteEpargne(String numeroDeCompte, Personne titulaire, double solde) {
        this(numeroDeCompte, titulaire, solde, null);
    }

    public CompteEpargne(String numeroDeCompte, Personne titulaire, double solde, LocalDate dateDernierRetrait) {
        super(numeroDeCompte, titulaire, solde);
        this.dateDernierRetrait = dateDernierRetrait;
    }

    // Pas de découvert sur une épargne !
    @Override
    public double getMontantRetirableMax() {
        return getSolde();
    }

    // Un seul taux, quelle que soit la situation du compte.
    @Override
    public double getTauxInterets() {
        return TAUX_INTERETS;
    }

    @Override
    public double calculerInterets() {
        return getSolde() * TAUX_INTERETS;
    }

    @Override
    public boolean retirer(double montant) {
        boolean effectue = super.retirer(montant);
        if (effectue) {
            dateDernierRetrait = LocalDate.now();
        }
        return effectue;
    }

    @Override
    public String getTypeDeCompte() {
        return "Compte épargne";
    }

    @Override
    protected String detailsSpecifiques() {
        return "Dernier retrait : %s | Taux appliqué : %.2f %%".formatted(
            dateDernierRetrait == null ? "aucun à ce jour" : dateDernierRetrait.format(FORMAT_DATE),
            TAUX_INTERETS * 100
        );
    }

    public LocalDate getDateDernierRetrait() {
        return dateDernierRetrait;
    }
}
