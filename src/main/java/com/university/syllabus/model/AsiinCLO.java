package com.university.syllabus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "asiin_clo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsiinCLO {
    @Id
    private String id;
    
    private String description;
    
    @OneToMany(mappedBy = "clo")
    private Set<AsiinAssessmentTool> assessmentTools = new HashSet<>();
} 