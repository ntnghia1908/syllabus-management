// CLOSLODTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.CLOSLO;
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
public class CLOSLODTO {
    private Integer sloId;
    private String sloDescription;
    private Integer learningOutcomeId;
    private String learningOutcomeDescription;
    private Float percentage;
    
    // Static method to convert Entity to DTO
    public static CLOSLODTO fromEntity(CLOSLO closlo) {
        CLOSLODTO dto = new CLOSLODTO();
        dto.setPercentage(closlo.getPercentage());
        
        if (closlo.getSlo() != null) {
            dto.setSloId(closlo.getSlo().getId());
            dto.setSloDescription(closlo.getSlo().getDescription());
        }
        
        if (closlo.getLearningOutcome() != null) {
            dto.setLearningOutcomeId(closlo.getLearningOutcome().getId());
            dto.setLearningOutcomeDescription(closlo.getLearningOutcome().getDescription());
        }
        
        return dto;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<CLOSLODTO> fromEntities(List<CLOSLO> closlos) {
        return closlos.stream().map(CLOSLODTO::fromEntity).collect(Collectors.toList());
    }
}