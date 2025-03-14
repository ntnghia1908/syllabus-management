// LearningOutcome.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "learning_outcome")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningOutcome {
    @Id
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    
    @Column(name = "description_vn", columnDefinition = "TEXT", nullable = false)
    private String descriptionVn;
}