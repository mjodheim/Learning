package Animalerie;

import Animalerie.Consultation.Consultation;
import Animalerie.Encodage.Encodage;
import Animalerie.animaux.Caractere;
import Animalerie.animaux.Chat;
import Animalerie.animaux.Chien;
import Animalerie.animaux.Logement;
import Animalerie.animaux.Oiseau;
import Animalerie.animaux.Sexe;
import Utils.Saisie;

import java.time.LocalDate;
import java.util.Scanner;

public final class Animalerie {
    private Animalerie() {
    }

    public static void run(Scanner scanner) {
        // Voir plus bas la méthode associée.
        Pension pension = pensionDeDepart();

        int choix;

        do {
            System.out.print("""
                ===== Animalerie =====
                1. Encodage
                2. Consultation
                3. Passer la nuit
                0. Revenir au menu principal
                """);

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                3
            );

            switch (choix) {
                case 1 -> Encodage.run(scanner, pension);
                case 2 -> Consultation.run(scanner, pension);
                case 3 -> Nuit.run(pension);
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }

    // Quelques pensionnaires déjà encodés.
    private static Pension pensionDeDepart() {
        Pension pension = new Pension();

        pension.accueillir(new Chat(
            "Filou", 4.2, 25, Sexe.MALE, 3, LocalDate.of(2025, 11, 4),
            Caractere.CALIN, true, false
        ));
        pension.accueillir(new Chien(
            "Naya", 27.5, 58, Sexe.FEMELLE, 5, LocalDate.of(2026, 2, 17),
            "Rouge", true, "Berger australien"
        ));
        pension.accueillir(new Oiseau(
            "Pistache", 0.09, 18, Sexe.FEMELLE, 2, LocalDate.of(2026, 6, 30),
            "Vert et jaune", Logement.VOLIERE
        ));

        return pension;
    }
}
