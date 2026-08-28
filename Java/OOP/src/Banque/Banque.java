package Banque;

import Banque.Gestion.Compte;
import Banque.Gestion.CompteCourant;
import Banque.Gestion.CompteEpargne;
import Banque.Gestion.Gestion;
import Banque.Operations.Operations;
import Utils.Saisie;

import java.time.LocalDate;
import java.util.*;

public class Banque {

    private static final String PREFIXE_NUMERO = "BE";
    private static final long NUMERO_MIN = 100_000_000_000L;
    private static final long NUMERO_MAX = 999_999_999_999L;

    private String nomBanque;

    private final HashMap<Personne, List<Compte>> comptes = new HashMap<>();

    // Une seule source de hasard pour toute la banque.
    private final Random hasard = new Random();

    public Banque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    // Un titulaire peut exister sans compte.
    public void ajouterTitulaire(Personne titulaire) {
        comptes.computeIfAbsent(titulaire, cle -> new ArrayList<>());
    }

    public void ajouterCompte(Compte compte) {
        comptes.computeIfAbsent(compte.getTitulaire(), cle -> new ArrayList<>()).add(compte);
    }

    public List<Personne> getTitulaires() {
        return List.copyOf(comptes.keySet());
    }

    public List<Compte> getComptes(Personne titulaire) {
        return Collections.unmodifiableList(comptes.getOrDefault(titulaire, List.of()));
    }

    public List<Compte> getTousLesComptes() {
        List<Compte> tous = new ArrayList<>();
        for (List<Compte> comptesDuTitulaire : comptes.values()) {
            tous.addAll(comptesDuTitulaire);
        }
        return tous;
    }

    // Un solde négatif compte lui aussi : il vient en déduction des avoirs.
    public double calculerAvoirs(Personne titulaire) {
        double total = 0;
        for (Compte compte : getComptes(titulaire)) {
            total += compte.getSolde();
        }
        return total;
    }

    public HashMap<Compte, Double> appliquerInterets() {
        HashMap<Compte, Double> interetsAppliques = new HashMap<>();
        for (Compte compte : getTousLesComptes()) {
            interetsAppliques.put(compte, compte.appliquerInterets());
        }
        return interetsAppliques;
    }

    public boolean aDesTitulaires() {
        return !comptes.isEmpty();
    }

    public boolean aDesComptes() {
        return !getTousLesComptes().isEmpty();
    }

    // Un numéro à douze chiffres précédé du code pays, tiré au hasard.
    // Vérification de l'existence d'un compte déjà créé avec ce même numéro
    public String genererNumeroDeCompte() {
        String numero;
        do {
            numero = PREFIXE_NUMERO + hasard.nextLong(NUMERO_MIN, NUMERO_MAX + 1);
        } while (numeroDejaUtilise(numero));
        return numero;
    }

    private boolean numeroDejaUtilise(String numero) {
        for (Compte compte : getTousLesComptes()) {
            if (compte.getNumeroDeCompte().equals(numero)) {
                return true;
            }
        }
        return false;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    public static void run(Scanner scanner) {

        // Ajout de quelques clients de base (voir plus bas).
        Banque banque = banqueDeDepart();

        int choix;

        do {
            System.out.printf("""
            ===== %s =====
            1. Gestion
            2. Opérations
            0. Revenir au menu principal
            """, banque.getNomBanque());

            choix = Saisie.lireEntier(
                scanner,
                "",
                "Choix invalide, veuillez ré-essayer...",
                0,
                2
            );

            switch (choix) {
                case 1 -> Gestion.run(scanner, banque);
                case 2 -> Operations.run(scanner, banque);
                default -> System.out.println("Retour au menu principal...");
            }
        } while (choix != 0);
    }

    // Création de la banque...
    // Quelques clients pour ne pas partir d'une banque vide.
    private static Banque banqueDeDepart() {
        Banque banque = new Banque("Banque de Mjödheim");

        Personne jean = new Personne("Dupont", "Jean", LocalDate.of(1978, 3, 14));
        Personne sophie = new Personne("Martin", "Sophie", LocalDate.of(1992, 11, 2));

        banque.ajouterCompte(new CompteCourant(banque.genererNumeroDeCompte(), jean, 1250.0, 500.0));
        banque.ajouterCompte(new CompteEpargne(banque.genererNumeroDeCompte(), jean, 8400.0,
            LocalDate.of(2026, 5, 27)));
        banque.ajouterCompte(new CompteCourant(banque.genererNumeroDeCompte(), sophie, 320.5, 1000.0));

        return banque;
    }
}
