// CourseAssessment.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "course_assessment")
@IdClass(CourseAssessmentId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseAssessment {
    @Id
    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    
    private Integer percentage;
}
