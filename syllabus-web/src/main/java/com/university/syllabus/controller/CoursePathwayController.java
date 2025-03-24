package com.university.syllabus.controller;

import com.university.syllabus.exception.ResourceNotFoundException;
import com.university.syllabus.model.Course;
import com.university.syllabus.model.CoursePathway;
import com.university.syllabus.model.CoursePathwayId;
import com.university.syllabus.model.Pathway;
import com.university.syllabus.model.Program;
import com.university.syllabus.service.CourseService;
import com.university.syllabus.service.CoursePathwayService;
import com.university.syllabus.service.PathwayService;
import com.university.syllabus.service.ProgramService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/coursePathways")
@RequiredArgsConstructor
public class CoursePathwayController {
    private final CoursePathwayService coursePathwayService;
    private final CourseService courseService;
    private final ProgramService programService;
    private final PathwayService pathwayService;
    
    @GetMapping("/program/{programId}/add")
    public String showAddCourseForm(@PathVariable Integer programId, Model model) {
        Program program = programService.getProgramById(programId)
            .orElseThrow(() -> new ResourceNotFoundException("Program", "id", programId));
        
        List<Course> allCourses = courseService.getAllCourses();
        List<Pathway> allPathways = pathwayService.getAllPathways();
        
        model.addAttribute("program", program);
        model.addAttribute("allCourses", allCourses);
        model.addAttribute("allPathways", allPathways);
        model.addAttribute("coursePathway", new CoursePathway());
        
        return "coursePathways/form";
    }
    
    @PostMapping("/program/{programId}/add")
    public String addCoursesToProgram(
            @PathVariable Integer programId,
            @RequestParam String courseId,
            @RequestParam Integer pathwayId,
            @RequestParam Integer year,
            @RequestParam Integer semester,
            RedirectAttributes redirectAttributes) {
        
        Program program = programService.getProgramById(programId)
            .orElseThrow(() -> new ResourceNotFoundException("Program", "id", programId));
        
        Course course = courseService.getCourseById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        
        Pathway pathway = pathwayService.getPathwayById(pathwayId)
            .orElseThrow(() -> new ResourceNotFoundException("Pathway", "id", pathwayId));
        
        CoursePathway coursePathway = new CoursePathway();
        coursePathway.setProgram(program);
        coursePathway.setCourse(course);
        coursePathway.setPathway(pathway);
        coursePathway.setYear(year);
        coursePathway.setSemester(semester);
        
        coursePathwayService.saveCoursePathway(coursePathway);
        
        redirectAttributes.addFlashAttribute("success", "Course added to program successfully");
        return "redirect:/programs/" + programId + "/curriculum";
    }
    
    @GetMapping("/program/{programId}/edit/{pathwayId}/{courseId}")
    public String showEditForm(
            @PathVariable Integer programId,
            @PathVariable Integer pathwayId,
            @PathVariable String courseId,
            Model model) {
        
        CoursePathwayId id = new CoursePathwayId(programId, pathwayId, courseId);
        CoursePathway coursePathway = coursePathwayService.getCoursePathwayById(id)
            .orElseThrow(() -> new ResourceNotFoundException("CoursePathway", "id", id.toString()));
        
        List<Pathway> allPathways = pathwayService.getAllPathways();
        
        model.addAttribute("coursePathway", coursePathway);
        model.addAttribute("allPathways", allPathways);
        model.addAttribute("programId", programId);
        model.addAttribute("pathwayId", pathwayId);
        model.addAttribute("courseId", courseId);
        
        return "coursePathways/edit";
    }
    
    @PostMapping("/program/{programId}/edit/{pathwayId}/{courseId}")
    public String updateCoursePathway(
            @PathVariable Integer programId,
            @PathVariable Integer pathwayId,
            @PathVariable String courseId,
            @RequestParam(required = false) Integer newPathwayId,
            @RequestParam Integer year,
            @RequestParam Integer semester,
            RedirectAttributes redirectAttributes) {
        
        CoursePathwayId id = new CoursePathwayId(programId, pathwayId, courseId);
        CoursePathway coursePathway = coursePathwayService.getCoursePathwayById(id)
            .orElseThrow(() -> new ResourceNotFoundException("CoursePathway", "id", id.toString()));
        
        // If the pathway is being changed, we need to delete the old one and create a new one
        if (newPathwayId != null && !newPathwayId.equals(pathwayId)) {
            coursePathwayService.deleteCoursePathway(id);
            
            Pathway newPathway = pathwayService.getPathwayById(newPathwayId)
                .orElseThrow(() -> new ResourceNotFoundException("Pathway", "id", newPathwayId));
            
            CoursePathway newCoursePathway = new CoursePathway();
            newCoursePathway.setProgram(coursePathway.getProgram());
            newCoursePathway.setCourse(coursePathway.getCourse());
            newCoursePathway.setPathway(newPathway);
            newCoursePathway.setYear(year);
            newCoursePathway.setSemester(semester);
            
            coursePathwayService.saveCoursePathway(newCoursePathway);
        } else {
            // Just update the year and semester
            coursePathway.setYear(year);
            coursePathway.setSemester(semester);
            coursePathwayService.saveCoursePathway(coursePathway);
        }
        
        redirectAttributes.addFlashAttribute("success", "Course details updated successfully");
        return "redirect:/programs/" + programId + "/curriculum";
    }
    
    @GetMapping("/program/{programId}/remove/{pathwayId}/{courseId}")
    public String removeCourseFromProgram(
            @PathVariable Integer programId,
            @PathVariable Integer pathwayId,
            @PathVariable String courseId,
            RedirectAttributes redirectAttributes) {
        
        CoursePathwayId id = new CoursePathwayId(programId, pathwayId, courseId);
        
        if (coursePathwayService.getCoursePathwayById(id).isPresent()) {
            coursePathwayService.deleteCoursePathway(id);
            redirectAttributes.addFlashAttribute("success", "Course removed from program successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Course not found in program");
        }
        
        return "redirect:/programs/" + programId + "/curriculum";
    }
} 