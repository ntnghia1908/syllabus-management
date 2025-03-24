package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "program_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String type;
    
    private String description;
    
    @OneToMany(mappedBy = "programType")
    @ToString.Exclude
    private Set<Program> programs = new HashSet<>();
}