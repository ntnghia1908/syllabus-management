// CourseController.java
package com.university.syllabus.controller;

import com.university.syllabus.dto.CourseDTO;
import com.university.syllabus.exception.ResourceNotFoundException;
import com.university.syllabus.model.Course;
import com.university.syllabus.model.CourseLevel;
import com.university.syllabus.model.LearningOutcome;
import com.university.syllabus.service.CourseService;
import com.university.syllabus.service.CourseLevelService;
import com.university.syllabus.service.LearningOutcomeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseLevelService courseLevelService;
    private final LearningOutcomeService learningOutcomeService;
    
    @GetMapping
    public String listCourses(Model model, @RequestParam(required = false) String search) {
        List<Course> courses;
        if (search != null && !search.isEmpty()) {
            courses = courseService.searchCourses(search);
            model.addAttribute("search", search);
        } else {
            courses = courseService.getAllCourses();
        }
        model.addAttribute("courses", courses);
        return "courses/list";
    }
    
    @GetMapping("/{id}")
    public String viewCourse(@PathVariable String id, Model model) {
        Course course = courseService.getCourseById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        
        List<LearningOutcome> learningOutcomes = learningOutcomeService.getLearningOutcomesByCourse(id);
        
        model.addAttribute("course", course);
        model.addAttribute("learningOutcomes", learningOutcomes);
        return "courses/view";
    }
    
    @GetMapping("/{id}/books")
    public String viewCourseBooks(@PathVariable String id, Model model) {
        return "redirect:/course-books/course/" + id;
    }
    
    @GetMapping("/new")
    public String newCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("courseLevels", courseLevelService.getAllCourseLevels());
        model.addAttribute("isNew", true);
        return "courses/form";
    }
    
    @GetMapping("/{id}/edit")
    public String editCourse(@PathVariable String id, Model model) {
        Course course = courseService.getCourseById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        
        model.addAttribute("course", course);
        model.addAttribute("courseLevels", courseLevelService.getAllCourseLevels());
        model.addAttribute("isNew", false);
        return "courses/form";
    }
    
    @PostMapping
    public String saveCourse(@Valid @ModelAttribute Course course, BindingResult bindingResult, 
                             RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courseLevels", courseLevelService.getAllCourseLevels());
            model.addAttribute("isNew", course.getId() == null);
            return "courses/form";
        }
        
        // Check if we're creating a new course with an ID that already exists
        if (course.getId() != null && courseService.getCourseById(course.getId()).isPresent() 
                && model.getAttribute("isNew") != null && (boolean) model.getAttribute("isNew")) {
            bindingResult.rejectValue("id", "error.course", "Course ID already exists");
            model.addAttribute("courseLevels", courseLevelService.getAllCourseLevels());
            return "courses/form";
        }
        
        courseService.saveCourse(course);
        
        redirectAttributes.addFlashAttribute("success", 
            course.getId() != null ? "Course updated successfully" : "Course created successfully");
        return "redirect:/courses";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteCourse(@PathVariable String id, RedirectAttributes redirectAttributes) {
        // You might want to add additional checks here, e.g., if the course is used in any program
        
        courseService.deleteCourse(id);
        redirectAttributes.addFlashAttribute("success", "Course deleted successfully");
        return "redirect:/courses";
    }
    
    @GetMapping("/level/{levelId}")
    public String listCoursesByLevel(@PathVariable Integer levelId, Model model) {
        CourseLevel level = courseLevelService.getCourseLevelById(levelId)
            .orElseThrow(() -> new ResourceNotFoundException("Course Level", "id", levelId));
        
        List<Course> courses = courseService.getCoursesByLevel(levelId);
        
        model.addAttribute("courses", courses);
        model.addAttribute("level", level);
        return "courses/list";
    }
    
    @GetMapping("/export")
    public String exportCourses(Model model) {
        List<CourseDTO> courses = CourseDTO.fromEntities(courseService.getAllCourses());
        model.addAttribute("courses", courses);
        return "courses/export";
    }
}
