package be.mjodheim.dal.entities;

import lombok.Data;

@Data
public class GradeEntity {
    private String grade;
    private Integer lower_bound;
    private Integer upper_bound;
}
