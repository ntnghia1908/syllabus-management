// AssessmentToolService.java
package com.university.syllabus.service;

import com.university.syllabus.model.AssessmentTool;
import com.university.syllabus.model.AssessmentToolId;
import com.university.syllabus.repository.AssessmentToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssessmentToolService {
    private final AssessmentToolRepository assessmentToolRepository;
    
    public List<AssessmentTool> getAllAssessmentTools() {
        return assessmentToolRepository.findAll();
    }
    
    public Optional<AssessmentTool> getAssessmentToolById(AssessmentToolId id) {
        return assessmentToolRepository.findById(id);
    }
    
    public List<AssessmentTool> getAssessmentToolsByCourse(String courseId) {
        return assessmentToolRepository.findByCourseId(courseId);
    }
    
    public AssessmentTool saveAssessmentTool(AssessmentTool assessmentTool) {
        return assessmentToolRepository.save(assessmentTool);
    }
    
    public void deleteAssessmentTool(AssessmentTool assessmentTool) {
        assessmentToolRepository.delete(assessmentTool);
    }
}