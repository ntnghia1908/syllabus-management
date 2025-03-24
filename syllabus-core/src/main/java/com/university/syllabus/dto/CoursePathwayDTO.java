package com.university.syllabus.dto;

import com.university.syllabus.model.Course;
import com.university.syllabus.model.CoursePathway;
import com.university.syllabus.model.Pathway;
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
public class CoursePathwayDTO {
    private Integer programId;
    private String programName;
    private Integer pathwayId;
    private String pathwayName;
    private String courseId;
    private String courseName;
    private Integer year;
    private Integer semester;
    
    // Static method to convert Entity to DTO
    public static CoursePathwayDTO fromEntity(CoursePathway coursePathway) {
        if (coursePathway == null) return null;
        
        CoursePathwayDTO dto = new CoursePathwayDTO();
        
        if (coursePathway.getProgram() != null) {
            dto.setProgramId(coursePathway.getProgram().getId());
            dto.setProgramName(coursePathway.getProgram().getName());
        }
        
        if (coursePathway.getPathway() != null) {
            dto.setPathwayId(coursePathway.getPathway().getId());
            dto.setPathwayName(coursePathway.getPathway().getPathway());
        }
        
        if (coursePathway.getCourse() != null) {
            dto.setCourseId(coursePathway.getCourse().getId());
            dto.setCourseName(coursePathway.getCourse().getName());
        }
        
        dto.setYear(coursePathway.getYear());
        dto.setSemester(coursePathway.getSemester());
        
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public CoursePathway toEntity() {
        CoursePathway coursePathway = new CoursePathway();
        
        Program program = new Program();
        program.setId(this.programId);
        coursePathway.setProgram(program);
        
        Pathway pathway = new Pathway();
        pathway.setId(this.pathwayId);
        coursePathway.setPathway(pathway);
        
        Course course = new Course();
        course.setId(this.courseId);
        coursePathway.setCourse(course);
        
        coursePathway.setYear(this.year);
        coursePathway.setSemester(this.semester);
        
        return coursePathway;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<CoursePathwayDTO> fromEntities(List<CoursePathway> coursePathways) {
        return coursePathways.stream()
                .map(CoursePathwayDTO::fromEntity)
                .collect(Collectors.toList());
    }
} 