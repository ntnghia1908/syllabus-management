package com.university.syllabus.dto;

import com.university.syllabus.model.Major;
import com.university.syllabus.model.Program;
import com.university.syllabus.model.ProgramType;
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
public class ProgramDTO {
    private Integer id;
    private String name;
    private Integer majorId;
    private String majorName;
    private Integer programTypeId;
    private String programTypeName;
    private String version;
    private Integer duration;
    private String validFrom;
    
    // Static method to convert Entity to DTO
    public static ProgramDTO fromEntity(Program program) {
        if (program == null) return null;
        
        ProgramDTO dto = new ProgramDTO();
        dto.setId(program.getId());
        dto.setName(program.getName());
        dto.setVersion(program.getVersion());
        dto.setDuration(program.getDuration());
        dto.setValidFrom(program.getValidFrom());
        
        if (program.getMajor() != null) {
            dto.setMajorId(program.getMajor().getId());
            dto.setMajorName(program.getMajor().getFullName());
        }
        
        if (program.getProgramType() != null) {
            dto.setProgramTypeId(program.getProgramType().getId());
            dto.setProgramTypeName(program.getProgramType().getType());
        }
        
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public Program toEntity() {
        Program program = new Program();
        program.setId(this.id);
        program.setName(this.name);
        program.setVersion(this.version);
        program.setDuration(this.duration);
        program.setValidFrom(this.validFrom);
        
        if (this.majorId != null) {
            Major major = new Major();
            major.setId(this.majorId);
            program.setMajor(major);
        }
        
        if (this.programTypeId != null) {
            ProgramType programType = new ProgramType();
            programType.setId(this.programTypeId);
            program.setProgramType(programType);
        }
        
        return program;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<ProgramDTO> fromEntities(List<Program> programs) {
        return programs.stream()
                .map(ProgramDTO::fromEntity)
                .collect(Collectors.toList());
    }
}