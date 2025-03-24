package com.university.syllabus.controller;

import com.university.syllabus.model.CoursePathway;
import com.university.syllabus.model.Pathway;
import com.university.syllabus.service.CoursePathwayService;
import com.university.syllabus.service.PathwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pathways")
@RequiredArgsConstructor
public class PathwayController {

    private final PathwayService pathwayService;
    private final CoursePathwayService coursePathwayService;

    @GetMapping
    public String listPathways(Model model) {
        List<Pathway> pathways = pathwayService.getAllPathways();
        model.addAttribute("pathways", pathways);
        return "pathways/list";
    }

    @GetMapping("/add")
    public String showAddPathwayForm(Model model) {
        model.addAttribute("pathway", new Pathway());
        return "pathways/form";
    }

    @PostMapping("/add")
    public String addPathway(@ModelAttribute Pathway pathway, RedirectAttributes redirectAttributes) {
        pathwayService.savePathway(pathway);
        redirectAttributes.addFlashAttribute("successMessage", "Pathway added successfully.");
        return "redirect:/pathways";
    }

    @GetMapping("/edit/{id}")
    public String showEditPathwayForm(@PathVariable Integer id, Model model) {
        Pathway pathway = pathwayService.getPathwayById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pathway ID: " + id));
        model.addAttribute("pathway", pathway);
        return "pathways/form";
    }

    @PostMapping("/edit")
    public String updatePathway(@ModelAttribute Pathway pathway, RedirectAttributes redirectAttributes) {
        pathwayService.savePathway(pathway);
        redirectAttributes.addFlashAttribute("successMessage", "Pathway updated successfully.");
        return "redirect:/pathways";
    }

    @GetMapping("/delete/{id}")
    public String deletePathway(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        List<CoursePathway> assignedCourses = coursePathwayService.getCoursePathwaysByPathway(id);
        
        if (!assignedCourses.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Cannot delete pathway because it has " + assignedCourses.size() + 
                " course(s) assigned to it. Please reassign these courses first.");
            return "redirect:/pathways";
        }
        
        pathwayService.deletePathway(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pathway deleted successfully.");
        return "redirect:/pathways";
    }
} 