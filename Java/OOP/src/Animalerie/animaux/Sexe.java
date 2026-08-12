package Animalerie.animaux;

public enum Sexe {
    MALE("Mâle"),
    FEMELLE("Femelle");

    private final String libelle;

    Sexe(String libelle) {
        this.libelle = libelle;
    }

    // Le libellé remplace le nom brut de la constante partout où la valeur est affichée
    // (menus de saisie compris, voir Saisie.lireEnumeration).
    @Override
    public String toString() {
        return libelle;
    }
}
