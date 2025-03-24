package com.university.syllabus.controller;

import com.university.syllabus.exception.ResourceNotFoundException;
import com.university.syllabus.model.Course;
import com.university.syllabus.model.CourseProgram;
import com.university.syllabus.model.CourseProgramId;
import com.university.syllabus.model.Program;
import com.university.syllabus.service.CourseService;
import com.university.syllabus.service.CourseProgramService;
import com.university.syllabus.service.ProgramService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/coursePrograms")
@RequiredArgsConstructor
public class CourseProgramController {
    private final CourseProgramService courseProgramService;
    private final CourseService courseService;
    private final ProgramService programService;
    
    @GetMapping("/program/{programId}/add")
    public String showAddCourseForm(@PathVariable Integer programId, Model model) {
        Program program = programService.getProgramById(programId)
            .orElseThrow(() -> new ResourceNotFoundException("Program", "id", programId));
        
        List<Course> allCourses = courseService.getAllCourses();
        
        model.addAttribute("program", program);
        model.addAttribute("allCourses", allCourses);
        model.addAttribute("courseProgram", new CourseProgram());
        
        return "coursePrograms/form";
    }
    
    @PostMapping("/program/{programId}/add")
    public String addCoursesToProgram(
            @PathVariable Integer programId,
            @RequestParam String courseId,
            @RequestParam String courseCode,
            @RequestParam Integer courseTypeId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer semester,
            RedirectAttributes redirectAttributes) {
        
        Program program = programService.getProgramById(programId)
            .orElseThrow(() -> new ResourceNotFoundException("Program", "id", programId));
        
        Course course = courseService.getCourseById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        
        CourseProgram courseProgram = new CourseProgram();
        courseProgram.setProgram(program);
        courseProgram.setCourse(course);
        courseProgram.setCourseCode(courseCode);
        courseProgram.setCourseTypeId(courseTypeId);
        courseProgram.setYear(year);
        courseProgram.setSemester(semester);
        
        courseProgramService.saveCourseProgram(courseProgram);
        
        redirectAttributes.addFlashAttribute("success", "Course added to program successfully");
        return "redirect:/programs/" + programId + "/curriculum";
    }
    
    @GetMapping("/program/{programId}/edit/{courseId}")
    public String showEditForm(
            @PathVariable Integer programId,
            @PathVariable String courseId,
            Model model) {
        
        CourseProgramId id = new CourseProgramId(courseId, programId);
        CourseProgram courseProgram = courseProgramService.getCourseProgramById(id)
            .orElseThrow(() -> new ResourceNotFoundException("CourseProgram", "id", id.toString()));
        
        model.addAttribute("courseProgram", courseProgram);
        
        return "coursePrograms/edit";
    }
    
    @PostMapping("/program/{programId}/edit/{courseId}")
    public String updateCourseProgram(
            @PathVariable Integer programId,
            @PathVariable String courseId,
            @RequestParam String courseCode,
            @RequestParam Integer courseTypeId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer semester,
            RedirectAttributes redirectAttributes) {
        
        CourseProgramId id = new CourseProgramId(courseId, programId);
        CourseProgram courseProgram = courseProgramService.getCourseProgramById(id)
            .orElseThrow(() -> new ResourceNotFoundException("CourseProgram", "id", id.toString()));
        
        courseProgram.setCourseCode(courseCode);
        courseProgram.setCourseTypeId(courseTypeId);
        courseProgram.setYear(year);
        courseProgram.setSemester(semester);
        
        courseProgramService.saveCourseProgram(courseProgram);
        
        redirectAttributes.addFlashAttribute("success", "Course details updated successfully");
        return "redirect:/programs/" + programId + "/curriculum";
    }
    
    @GetMapping("/program/{programId}/remove/{courseId}")
    public String removeCourseFromProgram(
            @PathVariable Integer programId,
            @PathVariable String courseId,
            RedirectAttributes redirectAttributes) {
        
        CourseProgramId id = new CourseProgramId(courseId, programId);
        
        if (courseProgramService.getCourseProgramById(id).isPresent()) {
            courseProgramService.deleteCourseProgram(id);
            redirectAttributes.addFlashAttribute("success", "Course removed from program successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Course not found in program");
        }
        
        return "redirect:/programs/" + programId + "/curriculum";
    }
} 