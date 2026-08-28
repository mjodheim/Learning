package models.personnage;

import java.util.Comparator;
import java.util.Objects;

public abstract class Personnage {
    private static int nextId = 0;

    private final int id;
    private int End;
    private int For;
    private int PV;

    private String nom;

    public Personnage() {
        this.id = nextId++;
        setEnd(getTop3());
        setFor(getTop3());
        setPV(getEnd() + modifier(getEnd()));
        setNom("");
    }

    // Getters et Setters

    public int getId() {
        return id;
    }

    public int getEnd() {
        return End;
    }

    public void setEnd(int end) {
        End = end;
    }

    public int getFor() {
        return For;
    }

    public void setFor(int aFor) {
        For = aFor;
    }

    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthodes

    private int getTop3(){
        return DeNFaces.lancerDe6(4).stream()
                .sorted(Comparator.reverseOrder()) // trier dans l'ordre décroissant
                .limit(3) // top 3
                .mapToInt(Integer::intValue) // pour chaque Integer, on récupère sa valeur primitive
                .sum(); // addition des 3 int
    }

    protected int modifier (int caracteristique){
        if (caracteristique < 5) return -1;
        if (caracteristique < 10) return 0;
        if(caracteristique < 15) return 1;
        return 2;
    }

    protected void recalculerPv(){
        setPV(getEnd() + modifier(getEnd()));
    }

    public void frappe(Personnage defendant){
        if (!this.estEnVie()) return; // On ne peut pas frapper si on est mort !
        int degats = DeNFaces.lancerDe4(1).get(0) +  modifier(this.getFor());
        defendant.setPV(defendant.getPV() - degats);
    }

    public boolean estEnVie(){
        return this.getPV() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personnage that = (Personnage) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Personnage{" +
                "id=" + id +
                ", End=" + End +
                ", For=" + For +
                ", PV=" + PV +
                '}';
    }
}
