// StudentDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.Student;
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
public class StudentDTO {
    private String id;
    private String name;
    private String major;
    private Integer batch;
    
    // Static method to convert Entity to DTO
    public static StudentDTO fromEntity(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setMajor(student.getMajor());
        dto.setBatch(student.getBatch());
        return dto;
    }
    
    // Static method to convert DTO to Entity
    public Student toEntity() {
        Student student = new Student();
        student.setId(this.id);
        student.setName(this.name);
        student.setMajor(this.major);
        student.setBatch(this.batch);
        return student;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<StudentDTO> fromEntities(List<Student> students) {
        return students.stream().map(StudentDTO::fromEntity).collect(Collectors.toList());
    }
}