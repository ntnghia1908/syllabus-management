// AssessmentRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {
}
