// CourseProgramRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.CourseProgram;
import com.university.syllabus.model.CourseProgramId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseProgramRepository extends JpaRepository<CourseProgram, CourseProgramId> {
    List<CourseProgram> findByProgramId(Integer programId);
    
    @Query("SELECT cp FROM CourseProgram cp WHERE cp.program.id = :programId ORDER BY cp.year, cp.semester")
    List<CourseProgram> findByProgramIdOrderedBySemesterAndYear(@Param("programId") Integer programId);
    
    List<CourseProgram> findByCourseId(String courseId);
}
