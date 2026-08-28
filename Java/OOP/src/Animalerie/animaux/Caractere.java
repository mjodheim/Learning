package Animalerie.animaux;

// Le caractère ne concerne que les chats : il n'a rien à faire dans Animal.
public enum Caractere {
    ENERGIQUE("Énergique"),
    FAROUCHE("Farouche"),
    CALIN("Câlin"),
    JOUEUR("Joueur"),
    INDEPENDANT("Indépendant"),
    PEUREUX("Peureux");

    private final String libelle;

    Caractere(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
