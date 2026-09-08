package be.mjodheim.dal.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentEntity {
    private int studentId;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private String login;
    private Integer sectionId;
    private Integer yearResult;
    private String courseId;
}
