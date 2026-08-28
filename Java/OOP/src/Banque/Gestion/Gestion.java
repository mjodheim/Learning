package Banque.Gestion;

import Banque.Banque;
import Banque.Personne;
import Banque.Utils.Selection;
import Banque.Utils.Utils;
import Utils.Saisie;

import java.time.LocalDate;
import java.util.Scanner;

public final class Gestion {

    private Gestion() {}

    public static void run(Scanner scanner, Banque banque) {
        int choix;

        do {
            System.out.print("""
                --- Gestion ---
                1. Créer un titulaire
                2. Ouvrir un compte courant
                3. Ouvrir un compte épargne
                0. Retour
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                3
            );

            switch (choix) {
                case 1 -> creerTitulaire(scanner, banque);
                case 2 -> ouvrirCompteCourant(scanner, banque);
                case 3 -> ouvrirCompteEpargne(scanner, banque);
                default -> System.out.println("Retour au menu de la banque...");
            }
        } while (choix != 0);
    }

    private static void creerTitulaire(Scanner scanner, Banque banque) {
        String nom = Saisie.lireTexte(
            scanner,
            "Nom : ",
            "Le nom ne peut pas être vide, veuillez ré-essayer..."
        );
        String prenom = Saisie.lireTexte(
            scanner,
            "Prénom : ",
            "Le prénom ne peut pas être vide, veuillez ré-essayer..."
        );
        LocalDate dateNaissance = Saisie.lireDate(
            scanner,
            "Date de naissance (jj/mm/aaaa) : ",
            "Date invalide, veuillez ré-essayer..."
        );

        Personne titulaire = new Personne(nom, prenom, dateNaissance);
        banque.ajouterTitulaire(titulaire);

        System.out.printf("%nTitulaire enregistré : %s%n%n", titulaire);
    }

    private static void ouvrirCompteCourant(Scanner scanner, Banque banque) {
        Personne titulaire = Selection.choisirTitulaire(scanner, banque, "Titulaire du nouveau compte courant :");
        if (titulaire == null) {
            return;
        }

        double soldeInitial = lireSoldeInitial(scanner);

        double ligneDeCredit = Saisie.lireReel(
            scanner,
            "Ligne de crédit (0 si aucun découvert autorisé) : ",
            valeur -> valeur >= 0,
            "La ligne de crédit ne peut pas être négative, veuillez ré-essayer..."
        );

        CompteCourant compte = new CompteCourant(
            banque.genererNumeroDeCompte(),
            titulaire,
            soldeInitial,
            ligneDeCredit
        );
        banque.ajouterCompte(compte);

        Utils.imprimerExtrait(compte, "Ouverture du compte", soldeInitial);
    }

    private static void ouvrirCompteEpargne(Scanner scanner, Banque banque) {
        Personne titulaire = Selection.choisirTitulaire(scanner, banque, "Titulaire du nouveau compte épargne :");

        // Vérification de l'existence du titulaire
        if (titulaire == null) {
            return;
        }

        double soldeInitial = lireSoldeInitial(scanner);

        // Un compte épargne tout neuf n'a pas encore de date de dernier retrait.
        CompteEpargne compte = new CompteEpargne(
            banque.genererNumeroDeCompte(),
            titulaire,
            soldeInitial
        );
        banque.ajouterCompte(compte);

        Utils.imprimerExtrait(compte, "Ouverture du compte", soldeInitial);
    }

    private static double lireSoldeInitial(Scanner scanner) {
        return Saisie.lireReel(
            scanner,
            "Solde d'ouverture : ",
            valeur -> valeur >= 0,
            "Le solde d'ouverture ne peut pas être négatif, veuillez ré-essayer..."
        );
    }
}
