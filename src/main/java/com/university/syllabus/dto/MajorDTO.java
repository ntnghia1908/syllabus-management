// MajorDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.Major;
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
public class MajorDTO {
    private Integer id;
    private Integer disciplineId;
    private String disciplineName;
    private String fullName;
    private String shortName;
    private int programCount;
    
    // Static method to convert Entity to DTO
    public static MajorDTO fromEntity(Major major) {
        MajorDTO dto = new MajorDTO();
        dto.setId(major.getId());
        dto.setFullName(major.getFullName());
        dto.setShortName(major.getShortName());
        dto.setProgramCount(major.getPrograms() != null ? major.getPrograms().size() : 0);
        
        if (major.getDiscipline() != null) {
            dto.setDisciplineId(major.getDiscipline().getId());
            dto.setDisciplineName(major.getDiscipline().getName());
        }
        
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public Major toEntity() {
        Major major = new Major();
        major.setId(this.id);
        major.setFullName(this.fullName);
        major.setShortName(this.shortName);
        return major;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<MajorDTO> fromEntities(List<Major> majors) {
        return majors.stream().map(MajorDTO::fromEntity).collect(Collectors.toList());
    }
}
