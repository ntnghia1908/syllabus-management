// Student.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String id;
    
    private String name;
    
    private String major;
    
    private Integer batch;
    
    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private Set<Result> results = new HashSet<>();
}

