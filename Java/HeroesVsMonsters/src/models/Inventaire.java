package models;

import java.util.Objects;

public class Inventaire {
    private int or;
    private int cuirs;

    public Inventaire() {
        setOr();
        setCuirs();
    }

    public int getOr() {
        return or;
    }

    private void setOr() {
        this.or = 0;
    }

    public int getCuirs() {
        return cuirs;
    }

    private void setCuirs() {
        this.cuirs = 0;
    }

    public void ajouterRessources (int or, int cuirs){
        this.or += or;
        this.cuirs += cuirs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Inventaire that = (Inventaire) o;
        return or == that.or && cuirs == that.cuirs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(or, cuirs);
    }

    @Override
    public String toString() {
        return "Inventaire{" +
                "or=" + or +
                ", cuirs=" + cuirs +
                '}';
    }
}
