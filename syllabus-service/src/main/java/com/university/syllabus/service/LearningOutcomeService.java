// LearningOutcomeService.java
package com.university.syllabus.service;

import com.university.syllabus.model.LearningOutcome;
import com.university.syllabus.repository.LearningOutcomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LearningOutcomeService {
    private final LearningOutcomeRepository learningOutcomeRepository;
    
    public List<LearningOutcome> getAllLearningOutcomes() {
        return learningOutcomeRepository.findAll();
    }
    
    public Optional<LearningOutcome> getLearningOutcomeById(Integer id) {
        return learningOutcomeRepository.findById(id);
    }
    
    public List<LearningOutcome> getLearningOutcomesByCourse(String courseId) {
        return learningOutcomeRepository.findByCourseId(courseId);
    }
    
    public LearningOutcome saveLearningOutcome(LearningOutcome learningOutcome) {
        return learningOutcomeRepository.save(learningOutcome);
    }
    
    public void deleteLearningOutcome(Integer id) {
        learningOutcomeRepository.deleteById(id);
    }
}