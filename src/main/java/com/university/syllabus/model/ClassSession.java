package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

@Entity
@Table(name = "class_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSession {
    @Id
    private String id;

    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    
    @Column(name = "semester")
    private Integer semester;
    
    @Column(name = "academic_year")
    private String academicYear;
    
    @Column(name = "group_theory")
    private Integer groupTheory;
}