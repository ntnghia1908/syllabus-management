package com.university.syllabus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asiin_assessment_tool")
@IdClass(AsiinAssessmentToolId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsiinAssessmentTool {
    @Id
    @Column(name = "clo_id")
    private String cloId;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;
    
    private Integer percentage;
    
    @ManyToOne
    @JoinColumn(name = "clo_id", insertable = false, updatable = false)
    private AsiinCLO clo;
} 