package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "program")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    @Id
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
    
    private String name;
    
    private Integer duration;
    
    private String version;
    
    @ManyToOne
    @JoinColumn(name = "program_type_id")
    private ProgramType programType;
    
    @Column(name = "valid_from")
    private String validFrom;
    
    @OneToMany(mappedBy = "program")
    @ToString.Exclude
    private Set<CourseProgram> coursePrograms = new HashSet<>();
}