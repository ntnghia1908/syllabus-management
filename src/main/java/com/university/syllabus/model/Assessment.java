// Assessment.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assessment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assessment {
    @Id
    private Integer id;
    
    private String type;
    
    @OneToMany(mappedBy = "assessment")
    @ToString.Exclude
    private Set<AssessmentTool> assessmentTools = new HashSet<>();
}