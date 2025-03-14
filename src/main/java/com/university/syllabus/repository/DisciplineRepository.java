// DisciplineRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
}