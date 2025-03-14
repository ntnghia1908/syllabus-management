// SLORepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.SLO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SLORepository extends JpaRepository<SLO, Integer> {
    List<SLO> findByCriteria(Integer criteria);
}
