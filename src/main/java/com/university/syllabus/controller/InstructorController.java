// InstructorController.java
package com.university.syllabus.controller;

import com.university.syllabus.exception.ResourceNotFoundException;
import com.university.syllabus.model.ClassSession;
import com.university.syllabus.model.Instructor;
import com.university.syllabus.service.ClassSessionService;
import com.university.syllabus.service.InstructorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;
    private final ClassSessionService classSessionService;
    
    @GetMapping
    public String listInstructors(Model model, @RequestParam(required = false) String search) {
        List<Instructor> instructors;
        if (search != null && !search.isEmpty()) {
            instructors = instructorService.searchInstructors(search);
            model.addAttribute("search", search);
        } else {
            instructors = instructorService.getAllInstructors();
        }
        model.addAttribute("instructors", instructors);
        return "instructors/list";
    }
    
    @GetMapping("/{id}")
    public String viewInstructor(@PathVariable String id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor", "id", id));
        
        List<ClassSession> classSessions = classSessionService.getClassSessionsByInstructor(id);
        
        model.addAttribute("instructor", instructor);
        model.addAttribute("classSessions", classSessions);
        return "instructors/view";
    }
    
    @GetMapping("/new")
    public String newInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("isNew", true);
        return "instructors/form";
    }
    
    @GetMapping("/{id}/edit")
    public String editInstructor(@PathVariable String id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor", "id", id));
        
        model.addAttribute("instructor", instructor);
        model.addAttribute("isNew", false);
        return "instructors/form";
    }
    
    @PostMapping
    public String saveInstructor(@Valid @ModelAttribute Instructor instructor, 
                                BindingResult bindingResult, 
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "instructors/form";
        }
        
        instructorService.saveInstructor(instructor);
        
        redirectAttributes.addFlashAttribute("success", 
            instructor.getId() != null ? "Instructor updated successfully" : "Instructor created successfully");
        return "redirect:/instructors";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteInstructor(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Instructor instructor = instructorService.getInstructorById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor", "id", id));
        
        // Check if instructor has any class sessions assigned
        List<ClassSession> classSessions = classSessionService.getClassSessionsByInstructor(id);
        if (!classSessions.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", 
                "Cannot delete instructor as they have " + classSessions.size() + 
                " class sessions assigned. Please reassign these classes first.");
            return "redirect:/instructors";
        }
        
        instructorService.deleteInstructor(id);
        redirectAttributes.addFlashAttribute("success", "Instructor deleted successfully");
        return "redirect:/instructors";
    }
    
    @GetMapping("/export")
    public String exportInstructors(Model model) {
        List<Instructor> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);
        return "instructors/export";
    }
}