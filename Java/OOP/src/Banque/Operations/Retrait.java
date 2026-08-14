package Banque.Operations;

import Banque.Banque;
import Banque.Gestion.Compte;
import Banque.Utils.Selection;
import Banque.Utils.Utils;
import Utils.Saisie;

import java.util.Scanner;

public final class Retrait {

    private Retrait() {
    }

    public static void run(Scanner scanner, Banque banque) {
        Compte compte = Selection.choisirCompte(scanner, banque, "Titulaire du compte à débiter :");
        if (compte == null) {
            return;
        }

        System.out.printf("Retrait maximum possible : %s%n",
            Utils.formaterMontant(compte.getMontantRetirableMax()));

        double montant = Saisie.lireReel(
            scanner,
            "Montant du retrait : ",
            valeur -> valeur > 0,
            "Le montant doit être strictement positif, veuillez ré-essayer..."
        );

        // Fonds insuffisants : ce n'est pas une erreur, c'est un refus, et le solde n'a pas bougé.
        if (!compte.retirer(montant)) {
            System.out.printf("%nRetrait refusé : vous ne pouvez pas retirer plus de %s.%n%n",
                Utils.formaterMontant(compte.getMontantRetirableMax()));
            return;
        }

        Utils.imprimerExtrait(compte, "Retrait", montant);
    }
}
