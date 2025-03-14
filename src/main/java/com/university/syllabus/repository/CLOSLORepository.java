// CLOSLORepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.CLOSLO;
import com.university.syllabus.model.CLOSLOId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CLOSLORepository extends JpaRepository<CLOSLO, CLOSLOId> {
    List<CLOSLO> findByLearningOutcomeId(Integer learningOutcomeId);
    
    List<CLOSLO> findBySloId(Integer sloId);
}
