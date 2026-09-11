package be.mjodheim.motocrudmvc.datas;

import be.mjodheim.motocrudmvc.models.Moto;

import java.util.ArrayList;
import java.util.List;

public class FakeDb {

    public static final List<Moto> motos = new ArrayList<>(List.of(
            new Moto(1L, "Honda", "CBR 600 RR", 600, "https://www.fr.honda.be/content/dam/central/motorcycles/colour-picker/supersports/cbr600rr/cbr600rr_2024/r-380_grandprixred/cbr600rr_2024_r-380_grandprixred.png"),
            new Moto(2L, "Yamaha", "MT-07", 689, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkVki89t0pKni8dFXLjoQPto4BcNsf4kn6VloyK48-Pg&s=10"),
            new Moto(3L, "Kawasaki", "Z900", 948, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiQqcJWNzfNXOeBk8jv4S1d0Nw0VZkyfG1M-Z3MqnRhg&s=10"),
            new Moto(4L, "Ducati", "Monster", 937, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHTkbwkIRFW-RIU07rLhzK2kQ3o9tIpTGbwac00xXnFg&s=10"),
            new Moto(5L, "BMW", "S 1000 RR", 999, "")
    ));

    private static long nextId = 6L;

    public static Long nextId() {
        return nextId++;
    }

    private FakeDb() {
    }
}
