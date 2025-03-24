// SLODTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.SLO;
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
public class SLODTO {
    private Integer id;
    private String description;
    private Integer criteria;
    
    // Static method to convert Entity to DTO
    public static SLODTO fromEntity(SLO slo) {
        SLODTO dto = new SLODTO();
        dto.setId(slo.getId());
        dto.setDescription(slo.getDescription());
        dto.setCriteria(slo.getCriteria());
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public SLO toEntity() {
        SLO slo = new SLO();
        slo.setId(this.id);
        slo.setDescription(this.description);
        slo.setCriteria(this.criteria);
        return slo;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<SLODTO> fromEntities(List<SLO> slos) {
        return slos.stream().map(SLODTO::fromEntity).collect(Collectors.toList());
    }
}