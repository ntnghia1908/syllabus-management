package com.university.syllabus.repository;

import com.university.syllabus.model.CoursePathway;
import com.university.syllabus.model.CoursePathwayId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePathwayRepository extends JpaRepository<CoursePathway, CoursePathwayId> {
    List<CoursePathway> findByProgramId(Integer programId);
    
    @Query("SELECT cp FROM CoursePathway cp WHERE cp.program.id = :programId ORDER BY cp.year, cp.semester")
    List<CoursePathway> findByProgramIdOrderedBySemesterAndYear(@Param("programId") Integer programId);
    
    List<CoursePathway> findByCourseId(String courseId);
    
    List<CoursePathway> findByPathwayId(Integer pathwayId);
} 