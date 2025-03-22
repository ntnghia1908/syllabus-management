package com.university.syllabus.service;

import com.university.syllabus.model.CoursePathway;
import com.university.syllabus.model.CoursePathwayId;
import com.university.syllabus.repository.CoursePathwayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursePathwayService {
    private final CoursePathwayRepository coursePathwayRepository;
    
    public List<CoursePathway> getAllCoursePathways() {
        return coursePathwayRepository.findAll();
    }
    
    public Optional<CoursePathway> getCoursePathwayById(CoursePathwayId id) {
        return coursePathwayRepository.findById(id);
    }
    
    public List<CoursePathway> getCoursePathwaysByProgram(Integer programId) {
        return coursePathwayRepository.findByProgramId(programId);
    }
    
    public List<CoursePathway> getCoursePathwaysByProgramOrderedBySemesterAndYear(Integer programId) {
        return coursePathwayRepository.findByProgramIdOrderedBySemesterAndYear(programId);
    }
    
    public List<CoursePathway> getCoursePathwaysByCourse(String courseId) {
        return coursePathwayRepository.findByCourseId(courseId);
    }
    
    public List<CoursePathway> getCoursePathwaysByPathway(Integer pathwayId) {
        return coursePathwayRepository.findByPathwayId(pathwayId);
    }
    
    public CoursePathway saveCoursePathway(CoursePathway coursePathway) {
        return coursePathwayRepository.save(coursePathway);
    }
    
    public void deleteCoursePathway(CoursePathwayId id) {
        coursePathwayRepository.deleteById(id);
    }
} 