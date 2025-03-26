package com.university.syllabus.repository;

import com.university.syllabus.model.CourseBook;
import com.university.syllabus.model.CourseBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseBookRepository extends JpaRepository<CourseBook, CourseBookId> {
    List<CourseBook> findByBookId(Long bookId);
    List<CourseBook> findByCourseId(String courseId);
    void deleteByBookIdAndCourseId(Long bookId, String courseId);
} 