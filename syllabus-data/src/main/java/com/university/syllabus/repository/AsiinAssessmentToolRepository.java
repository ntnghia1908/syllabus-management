package com.university.syllabus.repository;

import com.university.syllabus.model.AsiinAssessmentTool;
import com.university.syllabus.model.AsiinAssessmentToolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsiinAssessmentToolRepository extends JpaRepository<AsiinAssessmentTool, AsiinAssessmentToolId> {
    List<AsiinAssessmentTool> findByCourseId(String courseId);
    void deleteAllByCourseId(String courseId);
} 