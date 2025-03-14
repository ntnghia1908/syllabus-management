// CourseProgramService.java
package com.university.syllabus.service;

import com.university.syllabus.model.CourseProgram;
import com.university.syllabus.model.CourseProgramId;
import com.university.syllabus.repository.CourseProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseProgramService {
    private final CourseProgramRepository courseProgramRepository;
    
    public List<CourseProgram> getAllCoursePrograms() {
        return courseProgramRepository.findAll();
    }
    
    public Optional<CourseProgram> getCourseProgramById(CourseProgramId id) {
        return courseProgramRepository.findById(id);
    }
    
    public List<CourseProgram> getCourseProgramsByProgram(Integer programId) {
        return courseProgramRepository.findByProgramId(programId);
    }
    
    public List<CourseProgram> getCourseProgramsByCourse(String courseId) {
        return courseProgramRepository.findByCourseId(courseId);
    }
    
    public CourseProgram saveCourseProgram(CourseProgram courseProgram) {
        return courseProgramRepository.save(courseProgram);
    }
    
    public void deleteCourseProgram(CourseProgramId id) {
        courseProgramRepository.deleteById(id);
    }
}