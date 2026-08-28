package models.personnage.monsters;

import models.personnage.Personnage;

public abstract class Monstre extends Personnage {

    private int or;
    private int cuir;

    public Monstre() {
        setOr(0);
        setCuir(0);
        this.setNom("");
    }

    public int getOr() {
        return this.or;
    }

    protected void setOr(int or) {
        this.or = or;
    }

    public int getCuir() {
        return this.cuir;
    }

    protected void setCuir(int cuir) {
        this.cuir = cuir;
    }

}
