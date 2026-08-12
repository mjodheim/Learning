package Animalerie;

import Animalerie.animaux.Animal;

public final class FicheAnimal {
    private FicheAnimal() {
    }

    /* Un seul point d'affichage pour toutes les espèces : le paramètre est un Animal,
     * et c'est la classe réelle de l'objet qui décide du contenu des lignes.
     */
    public static void run(Animal animal) {
        System.out.printf("%s%n%n", animal.decrire());
    }
}
