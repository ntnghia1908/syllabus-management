package com.university.syllabus.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer year;
    
    // Courses associated with this book
    private List<CourseBookDTO> courses = new ArrayList<>();
} 