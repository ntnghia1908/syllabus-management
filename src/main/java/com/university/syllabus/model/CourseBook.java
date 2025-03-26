package com.university.syllabus.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseBook {
    
    @EmbeddedId
    private CourseBookId id = new CourseBookId();
    
    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;
    
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;
    
    @Column(name = "type")
    private String type; // e.g., "TEXTBOOK", "REFERENCE", etc.
    
    public CourseBook(Book book, Course course, String type) {
        this.book = book;
        this.course = course;
        this.type = type;
        this.id = new CourseBookId(book.getId(), course.getId());
    }
} 