// HomeController.java
package com.university.syllabus.controller;

import com.university.syllabus.config.AppConfig;
import com.university.syllabus.service.CourseService;
import com.university.syllabus.service.InstructorService;
import com.university.syllabus.service.ProgramService;
import com.university.syllabus.service.ClassSessionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CourseService courseService;
    private final InstructorService instructorService;
    private final ProgramService programService;
    private final ClassSessionService classSessionService;
    private final AppConfig appConfig;
    
    @GetMapping("/")
    public String index(Model model) {
        // Add counts for dashboard statistics
        long courseCount = courseService.getAllCourses().size();
        long instructorCount = instructorService.getAllInstructors().size();
        long programCount = programService.getAllPrograms().size();
        
        // Get current academic year
        String currentAcademicYear = appConfig.getAcademicYears().getCurrent();
        
        model.addAttribute("courseCount", courseCount);
        model.addAttribute("instructorCount", instructorCount);
        model.addAttribute("programCount", programCount);
        model.addAttribute("currentAcademicYear", currentAcademicYear);
        model.addAttribute("appName", appConfig.getName());
        model.addAttribute("appVersion", appConfig.getVersion());
        
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }
}
