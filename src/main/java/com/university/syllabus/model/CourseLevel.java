// CourseLevel.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "course_level")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseLevel {
    @Id
    private Integer id;
    
    private String level;
}