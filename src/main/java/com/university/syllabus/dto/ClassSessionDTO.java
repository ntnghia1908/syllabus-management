package com.university.syllabus.dto;

import com.university.syllabus.model.ClassSession;
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
public class ClassSessionDTO {
    private String id;
    private String courseId;
    private String courseName;
    private Integer semester;
    private String academicYear;
    private Integer groupTheory;
    
    // Static method to convert Entity to DTO
    public static ClassSessionDTO fromEntity(ClassSession classSession) {
        ClassSessionDTO dto = new ClassSessionDTO();
        dto.setId(classSession.getId());
        dto.setSemester(classSession.getSemester());
        dto.setAcademicYear(classSession.getAcademicYear());
        dto.setGroupTheory(classSession.getGroupTheory());
        
        if (classSession.getCourse() != null) {
            dto.setCourseId(classSession.getCourse().getId());
            dto.setCourseName(classSession.getCourse().getName());
        }
        
        return dto;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<ClassSessionDTO> fromEntities(List<ClassSession> classSessions) {
        return classSessions.stream().map(ClassSessionDTO::fromEntity).collect(Collectors.toList());
    }
}