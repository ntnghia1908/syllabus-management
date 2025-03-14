// ProgramRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
    List<Program> findByMajorId(Integer majorId);
    
    @Query("SELECT DISTINCT p.version FROM Program p ORDER BY p.version DESC")
    List<String> findAllVersions();
}
