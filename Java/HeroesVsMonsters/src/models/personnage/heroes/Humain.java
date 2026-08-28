package models.personnage.heroes;

public class Humain extends Hero {
    public Humain(String nom) {
        super(nom);
        this.setFor(this.getFor()+1);
        this.setEnd(this.getEnd()+1);
        recalculerPv();
    }
}
