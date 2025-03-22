package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "course_pathway")
@IdClass(CoursePathwayId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursePathway {
    @Id
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "pathway_id")
    private Pathway pathway;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @Column(name = "semester", nullable = false)
    private Integer semester;
    
    @Column(name = "year", nullable = false)
    private Integer year;
} 