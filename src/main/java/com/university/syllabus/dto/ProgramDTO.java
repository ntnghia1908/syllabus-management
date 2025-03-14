// ProgramDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.Program;
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
    private String disciplineName;
    private Integer duration;
    private String version;
    private String type;
    
    // Static method to convert Entity to DTO
    public static ProgramDTO fromEntity(Program program) {
        ProgramDTO dto = new ProgramDTO();
        dto.setId(program.getId());
        dto.setName(program.getName());
        dto.setDuration(program.getDuration());
        dto.setVersion(program.getVersion());
        dto.setType(program.getType());
        
        if (program.getMajor() != null) {
            dto.setMajorId(program.getMajor().getId());
            dto.setMajorName(program.getMajor().getFullName());
            if (program.getMajor().getDiscipline() != null) {
                dto.setDisciplineName(program.getMajor().getDiscipline().getName());
            }
        }
        
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public Program toEntity() {
        Program program = new Program();
        program.setId(this.id);
        program.setName(this.name);
        program.setDuration(this.duration);
        program.setVersion(this.version);
        program.setType(this.type);
        return program;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<ProgramDTO> fromEntities(List<Program> programs) {
        return programs.stream().map(ProgramDTO::fromEntity).collect(Collectors.toList());
    }
}