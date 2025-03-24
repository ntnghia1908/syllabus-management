package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instructor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    private String id;
    
    @Column(nullable = true)
    private String name;
    
    @Column(nullable = true)
    private String degree;
    
    @Column(nullable = true)
    private String email;
    
    @OneToMany(mappedBy = "instructor")
    @ToString.Exclude
    private Set<ClassSession> classSessions = new HashSet<>();
    
    @ManyToMany(mappedBy = "instructors")
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "department_instructor",
        joinColumns = @JoinColumn(name = "instructor_id"),
        inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @ToString.Exclude
    private Set<Department> departments = new HashSet<>();
}