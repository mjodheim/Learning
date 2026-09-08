package be.mjodheim.dal.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfessorEntity {
    private int professorId;
    private String professorName;
    private String professorSurname;
    private int sectionId;
    private int professorOffice;
    private String professorEmail;
    private LocalDate professorHireDate;
    private int professorWage;
}
