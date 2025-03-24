// CourseAssessmentRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.CourseAssessment;
import com.university.syllabus.model.CourseAssessmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseAssessmentRepository extends JpaRepository<CourseAssessment, CourseAssessmentId> {
    List<CourseAssessment> findByCourseId(String courseId);
}