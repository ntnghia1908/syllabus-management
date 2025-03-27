package com.university.syllabus.repository;

import com.university.syllabus.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.courseBooks cb WHERE cb.course.id = :courseId")
    Page<Book> findByCourseBooksCourseId(@Param("courseId") String courseId, Pageable pageable);
    
    Page<Book> findByYear(Integer year, Pageable pageable);
    
    @Query("SELECT b FROM Book b JOIN b.courseBooks cb WHERE cb.course.id = :courseId AND b.year = :year")
    Page<Book> findByCourseBooksCourseIdAndYear(@Param("courseId") String courseId, @Param("year") Integer year, Pageable pageable);
} 