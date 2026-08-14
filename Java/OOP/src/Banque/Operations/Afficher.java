package Banque.Operations;

import Banque.Banque;
import Banque.Personne;
import Banque.Utils.Selection;
import Banque.Utils.Utils;

import java.util.Scanner;

public final class Afficher {

    private Afficher() {}

    // Tous les comptes d'une personne et leur total.
    public static void run(Scanner scanner, Banque banque) {
        Personne titulaire = Selection.choisirTitulaire(scanner, banque, "Titulaire dont on vérifie les avoirs :");

        if (titulaire == null) {
            return;
        }

        Utils.avoirs(banque, titulaire);
    }
}
