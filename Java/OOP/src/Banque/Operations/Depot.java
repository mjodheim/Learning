package Banque.Operations;

import Banque.Banque;
import Banque.Gestion.Compte;
import Banque.Utils.Selection;
import Banque.Utils.Utils;
import Utils.Saisie;

import java.util.Scanner;

public final class Depot {

    private Depot() {}

    public static void run(Scanner scanner, Banque banque) {
        Compte compte = Selection.choisirCompte(scanner, banque, "Titulaire du compte à créditer :");
        if (compte == null) {
            return;
        }

        double montant = Saisie.lireReel(
            scanner,
            "Montant du dépôt : ",
            valeur -> valeur > 0,
            "Le montant doit être strictement positif, veuillez ré-essayer..."
        );

        compte.deposer(montant);
        Utils.imprimerExtrait(compte, "Dépôt", montant);
    }
}
