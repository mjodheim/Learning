package Animalerie.Consultation;

import Animalerie.Pension;
import Animalerie.animaux.Chat;
import Animalerie.animaux.Chien;
import Animalerie.animaux.Oiseau;

public final class Recensement {
    private Recensement() {
    }

    public static void run(Pension pension) {
        System.out.println("\nRecensement par espèce :");
        System.out.printf("  Chats   : %d%n", pension.compter(Chat.class));
        System.out.printf("  Chiens  : %d%n", pension.compter(Chien.class));
        System.out.printf("  Oiseaux : %d%n", pension.compter(Oiseau.class));
        System.out.printf(
            "  Total   : %d animal/animaux, dont %d en vie%n%n",
            pension.getNombre(),
            pension.compterVivants()
        );
    }
}
