package models.personnage.heroes;

public class Nain extends Hero {
    public Nain(String nom) {
        super(nom);
        this.setEnd(this.getEnd()+2);
        recalculerPv();
    }


}
