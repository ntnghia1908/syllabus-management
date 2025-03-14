// AssessmentToolRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.AssessmentTool;
import com.university.syllabus.model.AssessmentToolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentToolRepository extends JpaRepository<AssessmentTool, AssessmentToolId> {
    List<AssessmentTool> findByCourseId(String courseId);
}