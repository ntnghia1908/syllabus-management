// AssessmentTool.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "assessment_tool")
@IdClass(AssessmentToolId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentTool {
    @Id
    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "loutcome_id")
    private LearningOutcome learningOutcome;
    
    private Float percentage;
}