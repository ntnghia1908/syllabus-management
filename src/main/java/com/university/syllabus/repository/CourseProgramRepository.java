// CourseProgramRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.CourseProgram;
import com.university.syllabus.model.CourseProgramId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseProgramRepository extends JpaRepository<CourseProgram, CourseProgramId> {
    List<CourseProgram> findByProgramId(Integer programId);
    
    List<CourseProgram> findByCourseId(String courseId);
}
