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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id", referencedColumnName = "id")
    private Assessment assessment;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    
    private Integer percentage;
}