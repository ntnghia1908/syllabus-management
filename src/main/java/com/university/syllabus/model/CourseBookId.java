package com.university.syllabus.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseBookId implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "book_id")
    private Long bookId;
    
    @Column(name = "course_id")
    private String courseId;
} 