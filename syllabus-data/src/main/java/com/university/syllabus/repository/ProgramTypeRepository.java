// ProgramTypeRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.ProgramType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramTypeRepository extends JpaRepository<ProgramType, Integer> {
    // Add custom query methods if needed
} 