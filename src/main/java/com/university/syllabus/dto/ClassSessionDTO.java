// ClassSessionDTO.java
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
    private Integer id;
    private String courseId;
    private String courseName;
    private Integer instructorId;
    private String instructorName;
    private Integer group;
    private Integer groupLab;
    private Integer semester;
    private String academicYear;
    
    // Static method to convert Entity to DTO
    public static ClassSessionDTO fromEntity(ClassSession classSession) {
        ClassSessionDTO dto = new ClassSessionDTO();
        dto.setId(classSession.getId());
        dto.setGroup(classSession.getGroup());
        dto.setGroupLab(classSession.getGroupLab());
        dto.setSemester(classSession.getSemester());
        dto.setAcademicYear(classSession.getAcademicYear());
        
        if (classSession.getCourse() != null) {
            dto.setCourseId(classSession.getCourse().getId());
            dto.setCourseName(classSession.getCourse().getName());
        }
        
        if (classSession.getInstructor() != null) {
            dto.setInstructorId(classSession.getInstructor().getId());
            dto.setInstructorName(classSession.getInstructor().getName());
        }
        
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public ClassSession toEntity() {
        ClassSession classSession = new ClassSession();
        classSession.setId(this.id);
        classSession.setGroup(this.group);
        classSession.setGroupLab(this.groupLab);
        classSession.setSemester(this.semester);
        classSession.setAcademicYear(this.academicYear);
        return classSession;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<ClassSessionDTO> fromEntities(List<ClassSession> classSessions) {
        return classSessions.stream().map(ClassSessionDTO::fromEntity).collect(Collectors.toList());
    }
}
