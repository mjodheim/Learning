package Banque.Operations;

import Banque.Banque;
import Banque.Gestion.Compte;
import Banque.Utils.Utils;
import Utils.Saisie;

import java.util.Map;
import java.util.Scanner;

public final class Interets {

    private Interets() {
    }

    public static void run(Scanner scanner, Banque banque) {
        if (!banque.aDesComptes()) {
            System.out.printf("%nAucun compte ouvert : rien sur quoi appliquer des intérêts.%n%n");
            return;
        }

        boolean confirme = Saisie.lireOuiNon(
            scanner,
            "Appliquer les intérêts à tous les comptes de la banque (o/n) ? ",
            "Répondez par o ou n, veuillez ré-essayer..."
        );
        if (!confirme) {
            System.out.printf("%nOpération annulée.%n%n");
            return;
        }

        Map<Compte, Double> interetsAppliques = banque.appliquerInterets();

        // Un extrait par compte, comme pour un dépôt ou un retrait.
        for (Map.Entry<Compte, Double> ligne : interetsAppliques.entrySet()) {
            Utils.imprimerExtrait(ligne.getKey(), "Application des intérêts", ligne.getValue());
        }
    }
}
