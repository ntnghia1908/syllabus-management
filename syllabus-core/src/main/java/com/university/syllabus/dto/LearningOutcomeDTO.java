// LearningOutcomeDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.LearningOutcome;
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
public class LearningOutcomeDTO {
    private Integer id;
    private String courseId;
    private String courseName;
    private String description;
    private String descriptionVn;
    
    // Static method to convert Entity to DTO
    public static LearningOutcomeDTO fromEntity(LearningOutcome learningOutcome) {
        LearningOutcomeDTO dto = new LearningOutcomeDTO();
        dto.setId(learningOutcome.getId());
        dto.setDescription(learningOutcome.getDescription());
        dto.setDescriptionVn(learningOutcome.getDescriptionVn());
        
        if (learningOutcome.getCourse() != null) {
            dto.setCourseId(learningOutcome.getCourse().getId());
            dto.setCourseName(learningOutcome.getCourse().getName());
        }
        
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public LearningOutcome toEntity() {
        LearningOutcome learningOutcome = new LearningOutcome();
        learningOutcome.setId(this.id);
        learningOutcome.setDescription(this.description);
        learningOutcome.setDescriptionVn(this.descriptionVn);
        return learningOutcome;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<LearningOutcomeDTO> fromEntities(List<LearningOutcome> learningOutcomes) {
        return learningOutcomes.stream().map(LearningOutcomeDTO::fromEntity).collect(Collectors.toList());
    }
}