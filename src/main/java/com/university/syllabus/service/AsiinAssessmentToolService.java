package com.university.syllabus.service;

import com.university.syllabus.model.AsiinAssessmentTool;
import com.university.syllabus.model.AsiinAssessmentToolId;
import com.university.syllabus.repository.AsiinAssessmentToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsiinAssessmentToolService {
    private final AsiinAssessmentToolRepository asiinAssessmentToolRepository;

    @Autowired
    public AsiinAssessmentToolService(AsiinAssessmentToolRepository asiinAssessmentToolRepository) {
        this.asiinAssessmentToolRepository = asiinAssessmentToolRepository;
    }

    public List<AsiinAssessmentTool> getAllAsiinAssessmentTools() {
        return asiinAssessmentToolRepository.findAll();
    }

    public Optional<AsiinAssessmentTool> getAsiinAssessmentToolById(AsiinAssessmentToolId id) {
        return asiinAssessmentToolRepository.findById(id);
    }

    public List<AsiinAssessmentTool> getAsiinAssessmentToolsByCourse(String courseId) {
        return asiinAssessmentToolRepository.findByCourseId(courseId);
    }

    public AsiinAssessmentTool saveAsiinAssessmentTool(AsiinAssessmentTool asiinAssessmentTool) {
        return asiinAssessmentToolRepository.save(asiinAssessmentTool);
    }

    public void deleteAsiinAssessmentTool(AsiinAssessmentTool asiinAssessmentTool) {
        asiinAssessmentToolRepository.delete(asiinAssessmentTool);
    }
    
    public void deleteAllByCourseId(String courseId) {
        asiinAssessmentToolRepository.deleteAllByCourseId(courseId);
    }
} 