package be.mjodheim.dal.entities;

import lombok.Data;

@Data
public class SectionEntity {
    private int sectionId;
    private String sectionName;
    private int delegateId;
}
