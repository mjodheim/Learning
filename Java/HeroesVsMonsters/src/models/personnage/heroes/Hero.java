package models.personnage.heroes;

import interfaces.Collecter;
import models.Inventaire;
import models.personnage.Personnage;
import models.personnage.monsters.Monstre;

public abstract class Hero extends Personnage implements Collecter {

    private final Inventaire inventaire = new Inventaire();

    public Hero(String nom) {
        this.setNom(nom);
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    @Override
    public void collecter(Monstre monstre) {
        if (monstre.estEnVie()) throw new IllegalStateException(
                "Le monstre n'est pas encore mort !"
        );
        this.inventaire.ajouterRessources(monstre.getOr(), monstre.getCuir());
    }
}
