package Banque.Utils;

import Banque.Banque;
import Banque.Gestion.Compte;
import Banque.Personne;
import Utils.Saisie;

import java.util.List;
import java.util.Scanner;

public final class Selection {

    private static final String CHOIX_INVALIDE = "Choix invalide, veuillez ré-essayer...";

    private Selection() {}

    // Renvoie null si la banque n'a aucun titulaire.
    public static Personne choisirTitulaire(Scanner scanner, Banque banque, String titre) {
        if (!banque.aDesTitulaires()) {
            System.out.printf("%nAucun titulaire enregistré pour l'instant.%n%n");
            return null;
        }

        List<Personne> titulaires = banque.getTitulaires();

        System.out.printf("%n%s%n", titre);
        for (int i = 0; i < titulaires.size(); i++) {
            Personne titulaire = titulaires.get(i);
            System.out.printf("%d. %s — %d compte(s)%n",
                i + 1, titulaire, banque.getComptes(titulaire).size());
        }
        System.out.print("0. Annuler%n");

        int choix = Saisie.lireEntier(scanner, "", CHOIX_INVALIDE, 0, titulaires.size());
        return choix == 0 ? null : titulaires.get(choix - 1);
    }

    // Renvoie null si le titulaire n'a aucun compte.
    public static Compte choisirCompte(Scanner scanner, Banque banque, String titre) {
        Personne titulaire = choisirTitulaire(scanner, banque, titre);

        if (titulaire == null) {
            return null;
        }

        List<Compte> comptes = banque.getComptes(titulaire);
        if (comptes.isEmpty()) {
            System.out.printf("%n%s n'a encore aucun compte ouvert.%n%n", titulaire.getNomComplet());
            return null;
        }

        System.out.printf("%nComptes de %s :%n", titulaire.getNomComplet());
        for (int i = 0; i < comptes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, comptes.get(i));
        }
        System.out.print("0. Annuler%n");

        int choix = Saisie.lireEntier(scanner, "", CHOIX_INVALIDE, 0, comptes.size());
        return choix == 0 ? null : comptes.get(choix - 1);
    }
}
