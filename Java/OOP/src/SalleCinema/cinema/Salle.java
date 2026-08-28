package SalleCinema.cinema;

public class Salle {

    // Commune à toutes les salles (static), jamais modifiée (final = const),
    // lisible depuis Main qui est dans un autre package (public).
    public static final int PLACES_MAX = 200;

    // Fixé à la construction et ne change plus jamais : private + final.
    private final String nom;

    // État interne de la salle : invisibles depuis l'extérieur de la classe.
    private int placesVendues;
    private double recette;

    // Lu par SalleVIP, qui est une sous-classe.
    // Non final : SalleVIP a le droit de le régler.
    protected int niveauSonore;

    // Lu par AgentDEntretien (même package) mais PAS par SalleVIP (autre package).
    // Protected laisserait passer SalleVIP.
    final int codeNettoyage;

    // Détails d'implémentation : personne d'autre n'en a besoin.
    private static final double PRIX_PLACE = 12.50;
    private static final double TAUX_TVA = 0.06;

    public Salle(String nom, int niveauSonore, int codeNettoyage) {
        this.nom = nom;
        this.niveauSonore = niveauSonore;
        this.codeNettoyage = codeNettoyage;
    }

    /* ============================================================
     * PARTIE B — Les méthodes
     * ============================================================ */

    // (1) Surcharge la plus courte : une place, plein tarif.
    // Elle ne fait que déléguer, voir (2).
    public boolean reserver() {
        return reserver(1);
    }

    // (2) nb places, plein tarif -> délègue aussi.
    public boolean reserver(int nb) {
        return reserver(nb, 0.0);
    }

    // (3) Logique métier ici
    public boolean reserver(int nb, double reduction) {
        // Trois gardes, trois sorties immédiates.
        if (nb <= 0) {
            return false;
        }
        // isFinite écarte NaN et l'infini. Sans lui, NaN traverserait la garde :
        // il rend fausse TOUTE comparaison, donc < 0 et > 1 sont faux tous les deux.
        if (!Double.isFinite(reduction) || reduction < 0 || reduction > 1) {
            return false;
        }
        // Voir (5)
        if (!placesDisponibles(nb)) {
            return false;
        }

        // Chemin nominal : lisible d'un seul trait, aucun if imbriqué.
        placesVendues += nb;
        recette += nb * PRIX_PLACE * (1 - reduction);
        return true;
    }

    // (5) Outil interne : invisible depuis l'extérieur.
    private boolean placesDisponibles(int nb) {
        return placesVendues + nb <= PLACES_MAX;
    }

    // (6) Nombre variable d'arguments : accepte zéro, un ou plusieurs billets.
    // Elle se contente de faire la somme : elle ne touche pas à la recette,
    // qui n'est alimentée que par reserver.
    public double encaisser(double... billets) {
        double total = 0;
        for (double billet : billets) {
            total += billet;
        }
        return total;
    }

    // (7) Ne dépend d'aucune salle en particulier -> static.
    public static double prixTTC(double ht) {
        return ht * (1 + TAUX_TVA);
    }

    // (8) final : une sous-classe ne peut pas la redéfinir.
    public final String getNom() {
        return nom;
    }

    // Accesseurs en lecture seule : l'extérieur peut consulter l'état sans jamais pouvoir le modifier directement.
    public int getPlacesVendues() {
        return placesVendues;
    }

    public double getRecette() {
        return recette;
    }

    public int getPlacesRestantes() {
        return PLACES_MAX - placesVendues;
    }
}
