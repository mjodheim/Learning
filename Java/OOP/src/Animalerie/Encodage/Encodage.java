package Animalerie.Encodage;

import Animalerie.FicheAnimal;
import Animalerie.Pension;
import Animalerie.animaux.Animal;
import Animalerie.animaux.Caractere;
import Animalerie.animaux.Chat;
import Animalerie.animaux.Chien;
import Animalerie.animaux.Logement;
import Animalerie.animaux.Oiseau;
import Utils.Saisie;

import java.util.Scanner;

public final class Encodage {
    private Encodage() {
    }

    public static void run(Scanner scanner, Pension pension) {
        int choix;

        do {
            System.out.print("""
                --- Encodage ---
                1. Encoder un chat
                2. Encoder un chien
                3. Encoder un oiseau
                0. Retour
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                3
            );

            // Trois constructeurs différents, un seul type déclaré : Animal.
            Animal animal = switch (choix) {
                case 1 -> creerChat(scanner);
                case 2 -> creerChien(scanner);
                case 3 -> creerOiseau(scanner);
                default -> null;
            };

            if (animal == null) {
                System.out.println("Retour au menu de l'animalerie...");
            } else {
                pension.accueillir(animal);
                System.out.println("Animal encodé :");
                FicheAnimal.run(animal);
            }
        } while (choix != 0);
    }

    private static Chat creerChat(Scanner scanner) {
        Formulaire.InfosCommunes infos = Formulaire.lire(scanner);

        Caractere caractere = Saisie.lireEnumeration(
            scanner,
            "Caractère :",
            Caractere.values(),
            "Choix invalide, veuillez ré-essayer..."
        );
        boolean griffesCoupees = Saisie.lireOuiNon(
            scanner,
            "Griffes coupées (o/n) ? ",
            "Répondez par o ou n, veuillez ré-essayer..."
        );
        boolean poilLong = Saisie.lireOuiNon(
            scanner,
            "Chat à poil long (o/n) ? ",
            "Répondez par o ou n, veuillez ré-essayer..."
        );

        return new Chat(
            infos.nom(), infos.poids(), infos.taille(), infos.sexe(), infos.age(), infos.dateArrivee(),
            caractere, griffesCoupees, poilLong
        );
    }

    private static Chien creerChien(Scanner scanner) {
        Formulaire.InfosCommunes infos = Formulaire.lire(scanner);

        String race = Saisie.lireTexte(
            scanner,
            "Race : ",
            "La race ne peut pas être vide, veuillez ré-essayer..."
        );
        String couleurCollier = Saisie.lireTexte(
            scanner,
            "Couleur du collier : ",
            "La couleur ne peut pas être vide, veuillez ré-essayer..."
        );
        boolean dresse = Saisie.lireOuiNon(
            scanner,
            "Chien dressé (o/n) ? ",
            "Répondez par o ou n, veuillez ré-essayer..."
        );

        return new Chien(
            infos.nom(), infos.poids(), infos.taille(), infos.sexe(), infos.age(), infos.dateArrivee(),
            couleurCollier, dresse, race
        );
    }

    private static Oiseau creerOiseau(Scanner scanner) {
        Formulaire.InfosCommunes infos = Formulaire.lire(scanner);

        String couleur = Saisie.lireTexte(
            scanner,
            "Couleur : ",
            "La couleur ne peut pas être vide, veuillez ré-essayer..."
        );
        Logement logement = Saisie.lireEnumeration(
            scanner,
            "Logement :",
            Logement.values(),
            "Choix invalide, veuillez ré-essayer..."
        );

        return new Oiseau(
            infos.nom(), infos.poids(), infos.taille(), infos.sexe(), infos.age(), infos.dateArrivee(),
            couleur, logement
        );
    }
}
