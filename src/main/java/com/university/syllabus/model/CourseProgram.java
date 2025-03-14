package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "course_program")
@IdClass(CourseProgramId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseProgram {
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
    
    @Column(name = "course_code", nullable = false)
    private String courseCode;
    
    @Column(name = "course_type_id", nullable = false)
    private Integer courseTypeId;
}