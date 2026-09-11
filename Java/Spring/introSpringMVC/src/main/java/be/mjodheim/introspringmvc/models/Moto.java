package be.mjodheim.introspringmvc.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Moto {
    private Long id;
    private String brand;
    private String model;
    private int cc;
    private String imageUrl;
}
