package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    private Integer id;
    
    private String name;
    
    @ManyToMany(mappedBy = "departments")
    @ToString.Exclude
    private Set<Instructor> instructors = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "course_department",
        joinColumns = @JoinColumn(name = "department_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();
}