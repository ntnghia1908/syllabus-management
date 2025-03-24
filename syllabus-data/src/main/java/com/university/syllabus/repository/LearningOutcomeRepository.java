// LearningOutcomeRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.LearningOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningOutcomeRepository extends JpaRepository<LearningOutcome, Integer> {
    List<LearningOutcome> findByCourseId(String courseId);
}
