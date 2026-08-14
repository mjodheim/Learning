package Banque.Utils;

import Banque.Banque;
import Banque.Gestion.Compte;
import Banque.Personne;

import java.util.List;
import java.util.Locale;

public final class Utils {

    private static final String SEPARATEUR = "=============================";

    private Utils() {}

    // Les montants s'affichent partout de la même façon : virgule à deux décimales.
    public static String formaterMontant(double montant) {
        return String.format(Locale.FRANCE, "%.2f €", montant);
    }

    // L'extrait de compte demandé par l'énoncé, imprimé après chaque opération.
    public static void imprimerExtrait(Compte compte, String operation, double montant) {
        System.out.printf("""

            %s
                   EXTRAIT DE COMPTE
            %s
            """, SEPARATEUR, SEPARATEUR);

        if (operation != null) {
            System.out.printf("Opération : %s de %s%n", operation, formaterMontant(montant));
        }

        System.out.printf("%s%n%s%n%n", compte.decrire(), SEPARATEUR);
    }

    // Les avoirs d'une personne : le détail de ses comptes, puis le total.
    public static void avoirs(Banque banque, Personne titulaire) {
        List<Compte> comptes = banque.getComptes(titulaire);

        System.out.printf("%n===== %s — Avoirs de %s =====%n",
            banque.getNomBanque(), titulaire.getNomComplet());

        if (comptes.isEmpty()) {
            System.out.printf("Aucun compte ouvert pour ce titulaire.%n%n");
            return;
        }

        for (Compte compte : comptes) {
            System.out.printf("%s%n", compte.decrire());
        }

        System.out.printf("%nTotal des avoirs (%d compte(s)) : %s%n%n",
            comptes.size(), formaterMontant(banque.calculerAvoirs(titulaire)));
    }
}
