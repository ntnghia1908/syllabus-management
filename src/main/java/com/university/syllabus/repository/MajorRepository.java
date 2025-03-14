// MajorRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
    List<Major> findByDisciplineId(Integer disciplineId);
}