// SyllabusController.java
package com.university.syllabus.controller;

import com.university.syllabus.model.*;
import com.university.syllabus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/syllabus")
public class SyllabusController {
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private LearningOutcomeService learningOutcomeService;
    
    @Autowired
    private AssessmentService assessmentService;
    
    @Autowired
    private CourseAssessmentService courseAssessmentService;
    
    @Autowired
    private AssessmentToolService assessmentToolService;
    
    @GetMapping("/{courseId}")
    public String viewSyllabus(@PathVariable String courseId, Model model) {
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            List<LearningOutcome> learningOutcomes = learningOutcomeService.getLearningOutcomesByCourse(courseId);
            List<CourseAssessment> courseAssessments = courseAssessmentService.getCourseAssessmentsByCourse(courseId);
            List<AssessmentTool> assessmentTools = assessmentToolService.getAssessmentToolsByCourse(courseId);
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            model.addAttribute("assessmentTools", assessmentTools);
            
            return "syllabus/view";
        }
        return "redirect:/courses";
    }
    
    @GetMapping("/{courseId}/edit")
    public String editSyllabus(@PathVariable String courseId, Model model) {
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            List<LearningOutcome> learningOutcomes = learningOutcomeService.getLearningOutcomesByCourse(courseId);
            List<CourseAssessment> courseAssessments = courseAssessmentService.getCourseAssessmentsByCourse(courseId);
            List<Assessment> allAssessments = assessmentService.getAllAssessments();
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            model.addAttribute("allAssessments", allAssessments);
            
            model.addAttribute("newLearningOutcome", new LearningOutcome());
            
            return "syllabus/edit";
        }
        return "redirect:/courses";
    }
    
    @PostMapping("/{courseId}/learning-outcome")
    public String addLearningOutcome(@PathVariable String courseId, @ModelAttribute LearningOutcome learningOutcome, RedirectAttributes redirectAttributes) {
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isPresent()) {
            learningOutcome.setCourse(courseOpt.get());
            learningOutcomeService.saveLearningOutcome(learningOutcome);
            redirectAttributes.addFlashAttribute("success", "Learning outcome added successfully");
        }
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/assessment")
    public String addCourseAssessment(@PathVariable String courseId, @RequestParam Integer assessmentId, @RequestParam Integer percentage, RedirectAttributes redirectAttributes) {
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        
        if (courseOpt.isPresent() && assessmentOpt.isPresent()) {
            CourseAssessment courseAssessment = new CourseAssessment();
            courseAssessment.setCourse(courseOpt.get());
            courseAssessment.setAssessment(assessmentOpt.get());
            courseAssessment.setPercentage(percentage);
            
            courseAssessmentService.saveCourseAssessment(courseAssessment);
            redirectAttributes.addFlashAttribute("success", "Assessment added successfully");
        }
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @GetMapping("/export/{courseId}")
    public String exportSyllabus(@PathVariable String courseId, Model model) {
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            List<LearningOutcome> learningOutcomes = learningOutcomeService.getLearningOutcomesByCourse(courseId);
            List<CourseAssessment> courseAssessments = courseAssessmentService.getCourseAssessmentsByCourse(courseId);
            List<AssessmentTool> assessmentTools = assessmentToolService.getAssessmentToolsByCourse(courseId);
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            model.addAttribute("assessmentTools", assessmentTools);
            
            return "syllabus/export";
        }
        return "redirect:/courses";
    }
}