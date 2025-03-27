package com.university.syllabus.repository;

import com.university.syllabus.model.AsiinCLO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsiinCLORepository extends JpaRepository<AsiinCLO, String> {
    // Add any custom query methods here if needed
} 