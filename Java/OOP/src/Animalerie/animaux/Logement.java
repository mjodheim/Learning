package Animalerie.animaux;

// Un oiseau vit soit en volière, soit en petite cage : deux valeurs, jamais une troisième.
// Une énumération dit ça mieux qu'un booléen ou qu'une chaîne libre.
public enum Logement {
    VOLIERE("Volière"),
    PETITE_CAGE("Petite cage");

    private final String libelle;

    Logement(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
