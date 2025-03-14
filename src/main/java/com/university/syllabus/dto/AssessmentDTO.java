// AssessmentDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.Assessment;
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
public class AssessmentDTO {
    private Integer id;
    private String type;
    
    // Static method to convert Entity to DTO
    public static AssessmentDTO fromEntity(Assessment assessment) {
        AssessmentDTO dto = new AssessmentDTO();
        dto.setId(assessment.getId());
        dto.setType(assessment.getType());
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public Assessment toEntity() {
        Assessment assessment = new Assessment();
        assessment.setId(this.id);
        assessment.setType(this.type);
        return assessment;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<AssessmentDTO> fromEntities(List<Assessment> assessments) {
        return assessments.stream().map(AssessmentDTO::fromEntity).collect(Collectors.toList());
    }
}