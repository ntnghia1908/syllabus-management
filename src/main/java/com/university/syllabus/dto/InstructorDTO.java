package com.university.syllabus.dto;

import com.university.syllabus.model.Instructor;
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
public class InstructorDTO {
    private String id;
    private String name;
    private String degree;
    private String email;
    private int classCount;
    
    // Static method to convert Entity to DTO
    public static InstructorDTO fromEntity(Instructor instructor) {
        InstructorDTO dto = new InstructorDTO();
        dto.setId(instructor.getId());
        dto.setName(instructor.getName());
        dto.setDegree(instructor.getDegree());
        dto.setEmail(instructor.getEmail());
        dto.setClassCount(instructor.getClassSessions() != null ? instructor.getClassSessions().size() : 0);
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public Instructor toEntity() {
        Instructor instructor = new Instructor();
        instructor.setId(this.id);
        instructor.setName(this.name);
        instructor.setDegree(this.degree);
        instructor.setEmail(this.email);
        return instructor;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<InstructorDTO> fromEntities(List<Instructor> instructors) {
        return instructors.stream().map(InstructorDTO::fromEntity).collect(Collectors.toList());
    }
}