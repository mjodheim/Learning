package models.personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class DeNFaces {

    private static final Random rand = new Random();

    private DeNFaces() {}

    // On reçoit le nombre de lancés à faire et on retourne une liste des résultats
    public static List<Integer> lancerDe6(int nombreDeLances){
        List<Integer> lance = new ArrayList<>();
        for(int i = 0; i < nombreDeLances; i++){
            lance.add(rand.nextInt(6)+1);
        }
        return lance;
    }

    public static List<Integer> lancerDe4(int nombreDeLances){
        List<Integer> lance = new ArrayList<>();
        for(int i = 0; i < nombreDeLances; i++){
            lance.add(rand.nextInt(4)+1);
        }
        return lance;
    }
}
