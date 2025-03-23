package com.university.syllabus.dto;

import com.university.syllabus.model.Pathway;
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
public class PathwayDTO {
    private Integer id;
    private String pathway;
    
    // Static method to convert Entity to DTO
    public static PathwayDTO fromEntity(Pathway pathway) {
        if (pathway == null) return null;
        
        return PathwayDTO.builder()
                .id(pathway.getId())
                .pathway(pathway.getPathway())
                .build();
    }
    
    // Static method to convert DTO to Entity
    public Pathway toEntity() {
        Pathway pathway = new Pathway();
        pathway.setId(this.id);
        pathway.setPathway(this.pathway);
        return pathway;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<PathwayDTO> fromEntities(List<Pathway> pathways) {
        return pathways.stream()
                .map(PathwayDTO::fromEntity)
                .collect(Collectors.toList());
    }
} 