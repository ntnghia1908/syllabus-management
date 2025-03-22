// ProgramController.java
package com.university.syllabus.controller;

import com.university.syllabus.exception.ResourceNotFoundException;
import com.university.syllabus.model.*;
import com.university.syllabus.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;
    private final MajorService majorService;
    private final DisciplineService disciplineService;
    private final CourseProgramService courseProgramService;
    private final ProgramTypeService programTypeService;
    
    @GetMapping
    public String listPrograms(Model model, 
                              @RequestParam(required = false) Integer disciplineId,
                              @RequestParam(required = false) String version) {
        List<Program> programs;
        
        if (disciplineId != null) {
            // Filter by discipline (via major)
            List<Major> majors = majorService.getMajorsByDiscipline(disciplineId);
            programs = programService.getAllPrograms().stream()
                      .filter(p -> p.getMajor() != null && 
                                  majors.contains(p.getMajor()))
                      .toList();
        } else if (version != null && !version.isEmpty()) {
            // Filter by version
            programs = programService.getAllPrograms().stream()
                      .filter(p -> version.equals(p.getVersion()))
                      .toList();
        } else {
            // No filter
            programs = programService.getAllPrograms();
        }
        
        List<Discipline> disciplines = disciplineService.getAllDisciplines();
        List<String> versions = programService.getAllVersions();
        
        model.addAttribute("programs", programs);
        model.addAttribute("disciplines", disciplines);
        model.addAttribute("versions", versions);
        model.addAttribute("disciplineId", disciplineId);
        model.addAttribute("version", version);
        
        return "programs/list";
    }
    
    @GetMapping("/{id}")
    public String viewProgram(@PathVariable Integer id, Model model) {
        Program program = programService.getProgramById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Program", "id", id));
        
        List<CourseProgram> coursePrograms = courseProgramService.getCourseProgramsByProgram(id);
        
        model.addAttribute("program", program);
        model.addAttribute("coursePrograms", coursePrograms);
        return "programs/view";
    }
    
    @GetMapping("/new")
    public String newProgram(Model model) {
        model.addAttribute("program", new Program());
        model.addAttribute("majors", majorService.getAllMajors());
        model.addAttribute("programTypes", programTypeService.getAllProgramTypes());
        model.addAttribute("isNew", true);
        return "programs/form";
    }
    
    @GetMapping("/{id}/edit")
    public String editProgram(@PathVariable Integer id, Model model) {
        Program program = programService.getProgramById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Program", "id", id));
        
        model.addAttribute("program", program);
        model.addAttribute("majors", majorService.getAllMajors());
        model.addAttribute("programTypes", programTypeService.getAllProgramTypes());
        model.addAttribute("isNew", false);
        return "programs/form";
    }
    
    @PostMapping
    public String saveProgram(@Valid @ModelAttribute Program program, 
                             BindingResult bindingResult, 
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("majors", majorService.getAllMajors());
            model.addAttribute("programTypes", programTypeService.getAllProgramTypes());
            model.addAttribute("isNew", program.getId() == null);
            return "programs/form";
        }
        
        programService.saveProgram(program);
        
        redirectAttributes.addFlashAttribute("success", 
            program.getId() != null ? "Program updated successfully" : "Program created successfully");
        return "redirect:/programs";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteProgram(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Program program = programService.getProgramById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Program", "id", id));
        
        // Check if program has any courses assigned
        List<CourseProgram> coursePrograms = courseProgramService.getCourseProgramsByProgram(id);
        if (!coursePrograms.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", 
                "Cannot delete program as it has " + coursePrograms.size() + 
                " courses assigned. Please remove these courses first.");
            return "redirect:/programs";
        }
        
        programService.deleteProgram(id);
        redirectAttributes.addFlashAttribute("success", "Program deleted successfully");
        return "redirect:/programs";
    }
    
    @GetMapping("/{id}/curriculum")
    public String viewCurriculum(@PathVariable Integer id, Model model) {
        Program program = programService.getProgramById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Program", "id", id));
        
        List<CourseProgram> coursePrograms = courseProgramService.getCourseProgramsByProgram(id);
        
        model.addAttribute("program", program);
        model.addAttribute("coursePrograms", coursePrograms);
        return "programs/curriculum";
    }
}