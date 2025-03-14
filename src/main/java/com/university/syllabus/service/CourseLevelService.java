// CourseLevelService.java
package com.university.syllabus.service;

import com.university.syllabus.model.CourseLevel;
import com.university.syllabus.repository.CourseLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseLevelService {
    private final CourseLevelRepository courseLevelRepository;
    
    public List<CourseLevel> getAllCourseLevels() {
        return courseLevelRepository.findAll();
    }
    
    public Optional<CourseLevel> getCourseLevelById(Integer id) {
        return courseLevelRepository.findById(id);
    }
    
    public CourseLevel saveCourseLevel(CourseLevel courseLevel) {
        return courseLevelRepository.save(courseLevel);
    }
    
    public void deleteCourseLevel(Integer id) {
        courseLevelRepository.deleteById(id);
    }
}