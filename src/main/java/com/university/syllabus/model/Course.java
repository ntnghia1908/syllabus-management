package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "course_level_id", nullable = false)
    private CourseLevel courseLevel;
    
    @Column(nullable = true)
    private String name;
    
    @Column(name = "name_vn", nullable = false)
    private String nameVn;
    
    @Column(name = "credit_theory", nullable = true)
    private Integer creditTheory;
    
    @Column(name = "credit_lab", nullable = true)
    private Integer creditLab;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<LearningOutcome> learningOutcomes = new HashSet<>();
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<ClassSession> classSessions = new HashSet<>();
    
    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private Set<CourseAssessment> courseAssessments = new HashSet<>();
    
    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private Set<CourseProgram> coursePrograms = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "course_instructor",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    @ToString.Exclude
    private Set<Instructor> instructors = new HashSet<>();
    
    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private Set<CourseBook> courseBooks = new HashSet<>();
    
    public Integer getTotalCredits() {
        int theory = creditTheory != null ? creditTheory : 0;
        int lab = creditLab != null ? creditLab : 0;
        return theory + lab;
    }
}