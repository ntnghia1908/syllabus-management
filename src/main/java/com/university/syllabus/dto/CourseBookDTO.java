package com.university.syllabus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseBookDTO {
    private Long bookId;
    private String courseId;
    private String bookTitle;
    private String bookAuthor;
    private String bookIsbn;
    private String courseName;
    private String type;
} 