// CourseService.java
package com.university.syllabus.service;

import com.university.syllabus.model.Course;
import com.university.syllabus.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }
    
    public List<Course> getCoursesByLevel(Integer levelId) {
        return courseRepository.findByCourseLevelId(levelId);
    }
    
    public List<Course> searchCourses(String query) {
        return courseRepository.searchCourses(query);
    }
    
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}