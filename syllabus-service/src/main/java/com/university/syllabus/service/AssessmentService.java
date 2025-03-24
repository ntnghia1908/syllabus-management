// AssessmentService.java
package com.university.syllabus.service;

import com.university.syllabus.model.Assessment;
import com.university.syllabus.repository.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssessmentService {
    private final AssessmentRepository assessmentRepository;
    
    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }
    
    public Optional<Assessment> getAssessmentById(Integer id) {
        return assessmentRepository.findById(id);
    }
    
    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }
    
    public void deleteAssessment(Integer id) {
        assessmentRepository.deleteById(id);
    }
}