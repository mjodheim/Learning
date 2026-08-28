package models.personnage.monsters;

import models.personnage.DeNFaces;

public class Dragonet extends Monstre{
    public Dragonet() {
        this.setCuir(DeNFaces.lancerDe4(1).get(0));
        this.setOr(DeNFaces.lancerDe6(1).get(0));
        this.setEnd(this.getEnd()+1);
        recalculerPv();
        this.setNom("Dragonet");
    }
}
