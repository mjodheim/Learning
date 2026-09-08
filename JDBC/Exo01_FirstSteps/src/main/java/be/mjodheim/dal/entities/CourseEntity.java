package be.mjodheim.dal.entities;

import lombok.Data;

@Data
public class CourseEntity {
    private String courseId;
    private String courseName;
    private int courseECTS;
    private int professorId;
}
