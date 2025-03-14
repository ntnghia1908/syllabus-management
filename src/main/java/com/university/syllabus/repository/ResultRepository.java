// ResultRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.Result;
import com.university.syllabus.model.ResultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, ResultId> {
    List<Result> findByStudentId(String studentId);
    
    List<Result> findByClassSessionId(Integer classSessionId);
}