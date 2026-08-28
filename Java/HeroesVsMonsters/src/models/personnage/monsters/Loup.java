package models.personnage.monsters;

import models.personnage.DeNFaces;

public class Loup extends Monstre {

    public Loup() {
        this.setCuir(DeNFaces.lancerDe4(1).get(0));
        this.setNom("Loup");
    }

}
