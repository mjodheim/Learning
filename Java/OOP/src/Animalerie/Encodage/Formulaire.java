package Animalerie.Encodage;

import Animalerie.animaux.Sexe;
import Utils.Saisie;

import java.time.LocalDate;
import java.util.Scanner;

/* La partie du formulaire qui est identique pour un chat, un chien ou un oiseau.
 * Écrite une seule fois : les trois encodages l'appellent avant de poser
 * leurs questions spécifiques.
 */
public final class Formulaire {
    private Formulaire() {
    }

    // Un record : juste un paquet de valeurs saisies, transporté jusqu'au constructeur.
    public record InfosCommunes(
        String nom,
        double poids,
        double taille,
        Sexe sexe,
        int age,
        LocalDate dateArrivee
    ) {
    }

    public static InfosCommunes lire(Scanner scanner) {
        String nom = Saisie.lireTexte(
            scanner,
            "Nom : ",
            "Le nom ne peut pas être vide, veuillez ré-essayer..."
        );

        double poids = Saisie.lireReel(
            scanner,
            "Poids (kg) : ",
            valeur -> valeur > 0,
            "Valeur invalide, veuillez ré-essayer..."
        );

        double taille = Saisie.lireReel(
            scanner,
            "Taille (cm) : ",
            valeur -> valeur > 0,
            "Valeur invalide, veuillez ré-essayer..."
        );

        Sexe sexe = Saisie.lireEnumeration(
            scanner,
            "Sexe :",
            Sexe.values(),
            "Choix invalide, veuillez ré-essayer..."
        );

        int age = Saisie.lireEntier(
            scanner,
            "Âge (années) : ",
            "Valeur invalide, veuillez ré-essayer...",
            0,
            50
        );

        LocalDate dateArrivee = Saisie.lireDate(
            scanner,
            "Date d'arrivée (jj/mm/aaaa, vide = aujourd'hui) : ",
            "Date invalide, veuillez ré-essayer...",
            LocalDate.now()
        );

        return new InfosCommunes(nom, poids, taille, sexe, age, dateArrivee);
    }
}
