// CourseDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.Course;
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
public class CourseDTO {
    private String id;
    private String name;
    private String nameVn;
    private Integer creditTheory;
    private Integer creditLab;
    private String description;
    private Integer courseLevelId;
    private String courseLevelName;
    
    // Static method to convert Entity to DTO
    public static CourseDTO fromEntity(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setNameVn(course.getNameVn());
        dto.setCreditTheory(course.getCreditTheory());
        dto.setCreditLab(course.getCreditLab());
        dto.setDescription(course.getDescription());
        if (course.getCourseLevel() != null) {
            dto.setCourseLevelId(course.getCourseLevel().getId());
            dto.setCourseLevelName(course.getCourseLevel().getLevel());
        }
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public Course toEntity() {
        Course course = new Course();
        course.setId(this.id);
        course.setName(this.name);
        course.setNameVn(this.nameVn);
        course.setCreditTheory(this.creditTheory);
        course.setCreditLab(this.creditLab);
        course.setDescription(this.description);
        return course;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<CourseDTO> fromEntities(List<Course> courses) {
        return courses.stream().map(CourseDTO::fromEntity).collect(Collectors.toList());
    }
    
    public Integer getTotalCredits() {
        int theory = creditTheory != null ? creditTheory : 0;
        int lab = creditLab != null ? creditLab : 0;
        return theory + lab;
    }
}
