package com.university.syllabus.controller.api;

import com.university.syllabus.dto.CourseDTO;
import com.university.syllabus.model.Course;
import com.university.syllabus.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseRestController {
    @Autowired
    private CourseService courseService;
    
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(CourseDTO.fromEntities(courses));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String id) {
        Optional<Course> courseOpt = courseService.getCourseById(id);
        return courseOpt.map(course -> ResponseEntity.ok(CourseDTO.fromEntity(course)))
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        Course course = courseDTO.toEntity();
        Course savedCourse = courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(CourseDTO.fromEntity(savedCourse));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable String id, @RequestBody CourseDTO courseDTO) {
        if (!id.equals(courseDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Course> courseOpt = courseService.getCourseById(id);
        if (courseOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Course course = courseDTO.toEntity();
        Course updatedCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(CourseDTO.fromEntity(updatedCourse));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        Optional<Course> courseOpt = courseService.getCourseById(id);
        if (courseOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CourseDTO>> searchCourses(@RequestParam String query) {
        List<Course> courses = courseService.searchCourses(query);
        return ResponseEntity.ok(CourseDTO.fromEntities(courses));
    }
}
