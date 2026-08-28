package Animalerie;

import Animalerie.animaux.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pension {

    private final List<Animal> animaux = new ArrayList<>();

    private final Random hasard = new Random();

    public void accueillir(Animal animal) {
        animaux.add(animal);
    }

    // Lecture seule : l'extérieur peut parcourir le registre, jamais y ajouter un animal
    // autrement que par accueillir().
    public List<Animal> getAnimaux() {
        return Collections.unmodifiableList(animaux);
    }

    public boolean estVide() {
        return animaux.isEmpty();
    }

    public int getNombre() {
        return animaux.size();
    }

    // Compte les pensionnaires d'une espèce donnée.
    public int compter(Class<? extends Animal> espece) {
        int total = 0;
        for (Animal animal : animaux) {
            if (espece.isInstance(animal)) {
                total++;
            }
        }
        return total;
    }

    public int compterVivants() {
        int total = 0;
        for (Animal animal : animaux) {
            if (animal.estVivant()) {
                total++;
            }
        }
        return total;
    }

    // Fait passer la nuit à tout le monde et renvoie ceux qui ne se sont pas réveillés.
    public List<Animal> passerLaNuit() {
        List<Animal> victimes = new ArrayList<>();
        for (Animal animal : animaux) {
            if (animal.passerLaNuit(hasard)) {
                victimes.add(animal);
            }
        }
        return victimes;
    }
}
