// ClassSessionService.java
package com.university.syllabus.service;

import com.university.syllabus.model.ClassSession;
import com.university.syllabus.repository.ClassSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassSessionService {
    private final ClassSessionRepository classSessionRepository;
    
    public List<ClassSession> getAllClassSessions() {
        return classSessionRepository.findAll();
    }
    
    public Optional<ClassSession> getClassSessionById(String id) {
        return classSessionRepository.findById(id);
    }
    
    public List<ClassSession> getClassSessionsByCourse(String courseId) {
        return classSessionRepository.findByCourseId(courseId);
    }
    
    public List<ClassSession> getClassSessionsByInstructor(String instructorId) {
        return classSessionRepository.findByInstructorId(instructorId);
    }
    
    public List<ClassSession> getClassSessionsByAcademicYear(String academicYear) {
        return classSessionRepository.findByAcademicYear(academicYear);
    }
    
    public List<String> getAllAcademicYears() {
        return classSessionRepository.findAllAcademicYears();
    }
    
    public ClassSession saveClassSession(ClassSession classSession) {
        return classSessionRepository.save(classSession);
    }
    
    public void deleteClassSession(String id) {
        classSessionRepository.deleteById(id);
    }
}