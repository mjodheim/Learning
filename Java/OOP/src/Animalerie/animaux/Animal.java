package Animalerie.animaux;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/* Ce que tous les pensionnaires ont en commun.
 * abstract : on n'accueille jamais "un animal" à l'animalerie,
 * toujours un chat, un chien ou un oiseau.
 */
public abstract class Animal {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // La fiche est figée à l'encodage : private + final, personne ne la retouche ensuite.
    private final String nom;
    private final double poids;   // en kg
    private final double taille;  // en cm
    private final Sexe sexe;
    private final int age;        // en années
    private final LocalDate dateArrivee;

    // Seul état qui évolue au fil du programme : voir passerLaNuit.
    private boolean vivant = true;

    protected Animal(String nom, double poids, double taille, Sexe sexe, int age, LocalDate dateArrivee) {
        this.nom = nom;
        this.poids = poids;
        this.taille = taille;
        this.sexe = sexe;
        this.age = age;
        this.dateArrivee = dateArrivee;
    }

    /* ============================================================
     * Ce que chaque espèce doit fournir
     * ============================================================ */

    // Le comportement commun demandé par l'énoncé : chaque espèce a sa voix.
    // Elle renvoie le cri au lieu de l'afficher : c'est le menu qui décide de l'affichage.
    public abstract String crier();

    public abstract String getEspece();

    // L'âge humain équivalent ne se calcule pas de la même façon d'une espèce à l'autre :
    // impossible d'en faire une formule unique dans Animal.
    public abstract int getAgeHumain();

    // 0,5 % pour les chats, 1 % pour les chiens, 3 % pour les oiseaux.
    public abstract double getProbabiliteDeDeces();

    // La partie de la fiche que seule la sous-classe connaît.
    protected abstract String detailsSpecifiques();

    /* ============================================================
     * Comportements communs
     * ============================================================ */

    /* Une nuit passe. Renvoie true si l'animal ne s'est pas réveillé.
     * Le tirage est fait ici, mais la source du hasard vient de la pension :
     * un seul Random pour toute l'animalerie.
     */
    public boolean passerLaNuit(Random hasard) {
        // Un animal déjà décédé ne meurt pas une seconde fois.
        if (!vivant) {
            return false;
        }
        if (hasard.nextDouble() >= getProbabiliteDeDeces()) {
            return false;
        }

        vivant = false;
        return true;
    }

    /* final : la fiche a toujours la même structure pour tous les animaux.
     * Seule sa dernière ligne change, et elle est déléguée à detailsSpecifiques().
     */
    public final String decrire() {
        return """
            %s — %s (%s) — %s
              Âge : %d an(s), soit %d ans en âge humain | Poids : %.2f kg | Taille : %.1f cm
              Arrivée le %s | Cri : « %s » | Risque de décès : %.1f %%
              %s""".formatted(
            nom,
            getEspece(),
            sexe,
            vivant ? "en pension" : "décédé",
            age,
            getAgeHumain(),
            poids,
            taille,
            dateArrivee.format(FORMAT_DATE),
            crier(),
            getProbabiliteDeDeces() * 100,
            detailsSpecifiques()
        );
    }

    // Petit utilitaire d'affichage réservé aux sous-classes, pour leurs booléens.
    protected static String ouiOuNon(boolean valeur) {
        return valeur ? "oui" : "non";
    }

    /* ============================================================
     * Accesseurs en lecture seule
     * ============================================================ */

    public final String getNom() {
        return nom;
    }

    public double getPoids() {
        return poids;
    }

    public double getTaille() {
        return taille;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public boolean estVivant() {
        return vivant;
    }
}
