package Banque.Gestion;

import Banque.Personne;
import Banque.Utils.Utils;

public class CompteCourant extends Compte {

    // Deux taux pour un seul type de compte : c'est la situation du solde qui tranche.
    private static final double TAUX_SOLDE_POSITIF = 0.03;   // 3 %
    private static final double TAUX_SOLDE_NEGATIF = 0.0975; // 9,75 %

    private double ligneDeCredit;

    // Un compte ouvert sans versement démarre à zéro.
    public CompteCourant(String numeroDeCompte, Personne titulaire, double ligneDeCredit) {
        this(numeroDeCompte, titulaire, 0, ligneDeCredit);
    }

    public CompteCourant(String numeroDeCompte, Personne titulaire, double solde, double ligneDeCredit) {
        super(numeroDeCompte, titulaire, solde);
        setLigneDeCredit(ligneDeCredit);
    }

    // Le compte courant autorise le découvert, jusqu'à sa ligne de crédit.
    @Override
    public double getMontantRetirableMax() {
        return getSolde() + ligneDeCredit;
    }

    @Override
    public double getTauxInterets() {
        return getSolde() >= 0 ? TAUX_SOLDE_POSITIF : TAUX_SOLDE_NEGATIF;
    }

    /* Un seul produit couvre les deux cas : sur un solde négatif le résultat est négatif
     * lui aussi, et les intérêts creusent donc le découvert (−1 000 € → −1 097,50 €).
     */
    @Override
    public double calculerInterets() {
        return getSolde() * getTauxInterets();
    }

    @Override
    public String getTypeDeCompte() {
        return "Compte courant";
    }

    @Override
    protected String detailsSpecifiques() {
        return "Ligne de crédit : %s | Découvert utilisé : %s | Taux appliqué : %.2f %%".formatted(
            Utils.formaterMontant(ligneDeCredit),
            Utils.formaterMontant(getSolde() < 0 ? -getSolde() : 0),
            getTauxInterets() * 100
        );
    }

    public double getLigneDeCredit() {
        return ligneDeCredit;
    }

    // L'énoncé impose une ligne de crédit positive ou nulle : une ligne à zéro
    // revient simplement à un compte courant sans découvert autorisé.
    public void setLigneDeCredit(double ligneDeCredit) {
        if (ligneDeCredit < 0) {
            throw new IllegalArgumentException("La ligne de crédit ne peut pas être négative.");
        }
        this.ligneDeCredit = ligneDeCredit;
    }
}
