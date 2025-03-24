// DisciplineDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.Discipline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisciplineDTO {
    private Integer id;
    private String name;
    private int majorCount;
    
    // Static method to convert Entity to DTO
    public static DisciplineDTO fromEntity(Discipline discipline) {
        DisciplineDTO dto = new DisciplineDTO();
        dto.setId(discipline.getId());
        dto.setName(discipline.getName());
        dto.setMajorCount(discipline.getMajors() != null ? discipline.getMajors().size() : 0);
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public Discipline toEntity() {
        Discipline discipline = new Discipline();
        discipline.setId(this.id);
        discipline.setName(this.name);
        return discipline;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<DisciplineDTO> fromEntities(List<Discipline> disciplines) {
        return disciplines.stream().map(DisciplineDTO::fromEntity).collect(Collectors.toList());
    }
}