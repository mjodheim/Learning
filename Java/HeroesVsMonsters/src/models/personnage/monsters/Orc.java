package models.personnage.monsters;

import models.personnage.DeNFaces;

public class Orc extends Monstre {

    public Orc() {
        this.setOr(DeNFaces.lancerDe6(1).get(0));
        this.setFor(this.getFor()+1);
        this.setNom("Orc");
    }
}
