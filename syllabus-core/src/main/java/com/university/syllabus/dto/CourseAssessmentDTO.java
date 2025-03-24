// CourseAssessmentDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.CourseAssessment;
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
public class CourseAssessmentDTO {
    private Integer assessmentId;
    private String assessmentType;
    private String courseId;
    private String courseName;
    private Integer percentage;
    
    // Static method to convert Entity to DTO
    public static CourseAssessmentDTO fromEntity(CourseAssessment courseAssessment) {
        CourseAssessmentDTO dto = new CourseAssessmentDTO();
        if (courseAssessment.getAssessment() != null) {
            dto.setAssessmentId(courseAssessment.getAssessment().getId());
            dto.setAssessmentType(courseAssessment.getAssessment().getType());
        }
        
        if (courseAssessment.getCourse() != null) {
            dto.setCourseId(courseAssessment.getCourse().getId());
            dto.setCourseName(courseAssessment.getCourse().getName());
        }
        
        dto.setPercentage(courseAssessment.getPercentage());
        
        return dto;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<CourseAssessmentDTO> fromEntities(List<CourseAssessment> courseAssessments) {
        return courseAssessments.stream().map(CourseAssessmentDTO::fromEntity).collect(Collectors.toList());
    }
}