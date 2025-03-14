// ClassSessionRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession, Integer> {
    List<ClassSession> findByCourseId(String courseId);
    
    List<ClassSession> findByInstructorId(Integer instructorId);
    
    @Query("SELECT cs FROM ClassSession cs WHERE cs.academicYear = :academicYear")
    List<ClassSession> findByAcademicYear(@Param("academicYear") String academicYear);
    
    @Query("SELECT DISTINCT cs.academicYear FROM ClassSession cs ORDER BY cs.academicYear DESC")
    List<String> findAllAcademicYears();
}
