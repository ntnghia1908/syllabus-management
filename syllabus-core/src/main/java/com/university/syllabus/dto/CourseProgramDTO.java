// CourseProgramDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.CourseProgram;
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
public class CourseProgramDTO {
    private String courseId;
    private String courseName;
    private Integer programId;
    private String programName;
    private String courseCode;
    private Integer courseTypeId;
    private String courseTypeDescription;
    
    // Helper to get course type description based on type ID
    public static String getCourseTypeDescription(Integer typeId) {
        if (typeId == null) return "Unknown";
        
        return switch(typeId) {
            case 1 -> "General Education";
            case 2 -> "Foundation";
            case 3 -> "Major Elective";
            case 4 -> "Major Core";
            case 5 -> "Capstone";
            case 6 -> "Other";
            default -> "Unknown";
        };
    }
    
    // Static method to convert Entity to DTO
    public static CourseProgramDTO fromEntity(CourseProgram courseProgram) {
        CourseProgramDTO dto = new CourseProgramDTO();
        dto.setCourseCode(courseProgram.getCourseCode());
        dto.setCourseTypeId(courseProgram.getCourseTypeId());
        dto.setCourseTypeDescription(getCourseTypeDescription(courseProgram.getCourseTypeId()));
        
        if (courseProgram.getCourse() != null) {
            dto.setCourseId(courseProgram.getCourse().getId());
            dto.setCourseName(courseProgram.getCourse().getName());
        }
        
        if (courseProgram.getProgram() != null) {
            dto.setProgramId(courseProgram.getProgram().getId());
            dto.setProgramName(courseProgram.getProgram().getName());
        }
        
        return dto;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<CourseProgramDTO> fromEntities(List<CourseProgram> coursePrograms) {
        return coursePrograms.stream().map(CourseProgramDTO::fromEntity).collect(Collectors.toList());
    }
}