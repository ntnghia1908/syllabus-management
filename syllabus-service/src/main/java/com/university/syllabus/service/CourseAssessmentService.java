// CourseAssessmentService.java
package com.university.syllabus.service;

import com.university.syllabus.model.CourseAssessment;
import com.university.syllabus.model.CourseAssessmentId;
import com.university.syllabus.repository.CourseAssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseAssessmentService {
    private final CourseAssessmentRepository courseAssessmentRepository;
    
    public List<CourseAssessment> getAllCourseAssessments() {
        return courseAssessmentRepository.findAll();
    }
    
    public Optional<CourseAssessment> getCourseAssessmentById(CourseAssessmentId id) {
        return courseAssessmentRepository.findById(id);
    }
    
    public List<CourseAssessment> getCourseAssessmentsByCourse(String courseId) {
        return courseAssessmentRepository.findByCourseId(courseId);
    }
    
    public CourseAssessment saveCourseAssessment(CourseAssessment courseAssessment) {
        return courseAssessmentRepository.save(courseAssessment);
    }
    
    public void deleteCourseAssessment(CourseAssessmentId id) {
        courseAssessmentRepository.deleteById(id);
    }
}