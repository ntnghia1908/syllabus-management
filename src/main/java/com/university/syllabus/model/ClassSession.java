// ClassSession.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "class_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSession {
    @Id
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;
    
    @Column(name = "`group`")
    private Integer group;
    
    @Column(name = "group_lab")
    private Integer groupLab;
    
    private Integer semester;
    
    @Column(name = "academic_year")
    private String academicYear;
}