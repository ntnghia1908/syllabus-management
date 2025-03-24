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
    
    /* Assessment Tool feature disabled
    @Autowired
    private AssessmentToolService assessmentToolService;
    */
    
    @Autowired
    private AsiinAssessmentToolService asiinAssessmentToolService;
    
    @Autowired
    private AsiinCLOService asiinCLOService;
    
    @GetMapping("/{courseId}")
    public String viewSyllabus(@PathVariable String courseId, Model model) {
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            List<LearningOutcome> learningOutcomes = learningOutcomeService.getLearningOutcomesByCourse(courseId);
            List<CourseAssessment> courseAssessments = courseAssessmentService.getCourseAssessmentsByCourse(courseId);
            // Assessment Tool feature disabled
            // List<AssessmentTool> assessmentTools = assessmentToolService.getAssessmentToolsByCourse(courseId);
            List<AsiinAssessmentTool> asiinAssessmentTools = asiinAssessmentToolService.getAsiinAssessmentToolsByCourse(courseId);
            // Filter out null values or items with null assessment property
            asiinAssessmentTools = asiinAssessmentTools.stream()
                .filter(tool -> tool != null && tool.getAssessment() != null)
                .toList();
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            // Assessment Tool feature disabled
            // model.addAttribute("assessmentTools", assessmentTools);
            model.addAttribute("asiinAssessmentTools", asiinAssessmentTools);
            
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
            // Assessment Tool feature disabled
            // List<AssessmentTool> assessmentTools = assessmentToolService.getAssessmentToolsByCourse(courseId);
            List<AsiinAssessmentTool> asiinAssessmentTools = asiinAssessmentToolService.getAsiinAssessmentToolsByCourse(courseId);
            // Filter out null values or items with null assessment property
            asiinAssessmentTools = asiinAssessmentTools.stream()
                .filter(tool -> tool != null && tool.getAssessment() != null)
                .toList();
            List<AsiinCLO> asiinCLOs = asiinCLOService.getAllAsiinCLOs();
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            model.addAttribute("allAssessments", allAssessments);
            // Assessment Tool feature disabled
            // model.addAttribute("assessmentTools", assessmentTools);
            model.addAttribute("asiinAssessmentTools", asiinAssessmentTools);
            model.addAttribute("asiinCLOs", asiinCLOs);
            
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
            // Assessment Tool feature disabled
            // List<AssessmentTool> assessmentTools = assessmentToolService.getAssessmentToolsByCourse(courseId);
            List<AsiinAssessmentTool> asiinAssessmentTools = asiinAssessmentToolService.getAsiinAssessmentToolsByCourse(courseId);
            // Filter out null values or items with null assessment property
            asiinAssessmentTools = asiinAssessmentTools.stream()
                .filter(tool -> tool != null && tool.getAssessment() != null)
                .toList();
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            // Assessment Tool feature disabled
            // model.addAttribute("assessmentTools", assessmentTools);
            model.addAttribute("asiinAssessmentTools", asiinAssessmentTools);
            
            return "syllabus/export";
        }
        return "redirect:/courses";
    }
    
    /* Assessment Tool feature disabled
    @PostMapping("/{courseId}/assessment-tool")
    public String addAssessmentTool(
            @PathVariable String courseId,
            @RequestParam Integer assessmentId,
            @RequestParam Integer learningOutcomeId,
            @RequestParam Float percentage,
            RedirectAttributes redirectAttributes) {
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        Optional<LearningOutcome> learningOutcomeOpt = learningOutcomeService.getLearningOutcomeById(learningOutcomeId);
        
        if (courseOpt.isPresent() && assessmentOpt.isPresent() && learningOutcomeOpt.isPresent()) {
            AssessmentTool assessmentTool = new AssessmentTool();
            assessmentTool.setCourse(courseOpt.get());
            assessmentTool.setAssessment(assessmentOpt.get());
            assessmentTool.setLearningOutcome(learningOutcomeOpt.get());
            assessmentTool.setPercentage(percentage);
            
            assessmentToolService.saveAssessmentTool(assessmentTool);
            redirectAttributes.addFlashAttribute("success", "Assessment tool added successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Could not add assessment tool - missing required data");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/assessment-tool/update")
    public String updateAssessmentTool(
            @PathVariable String courseId,
            @RequestParam Integer assessmentId,
            @RequestParam Integer learningOutcomeId,
            @RequestParam Float percentage,
            RedirectAttributes redirectAttributes) {
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        Optional<LearningOutcome> learningOutcomeOpt = learningOutcomeService.getLearningOutcomeById(learningOutcomeId);
        
        if (courseOpt.isPresent() && assessmentOpt.isPresent() && learningOutcomeOpt.isPresent()) {
            AssessmentToolId id = new AssessmentToolId(assessmentId, courseId, learningOutcomeId);
            Optional<AssessmentTool> toolOpt = assessmentToolService.getAssessmentToolById(id);
            
            if (toolOpt.isPresent()) {
                AssessmentTool assessmentTool = toolOpt.get();
                assessmentTool.setPercentage(percentage);
                
                assessmentToolService.saveAssessmentTool(assessmentTool);
                redirectAttributes.addFlashAttribute("success", "Assessment tool updated successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Assessment tool not found");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Could not update assessment tool - missing required data");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @GetMapping("/{courseId}/assessment-tool/delete")
    public String deleteAssessmentTool(
            @PathVariable String courseId,
            @RequestParam Integer assessmentId,
            @RequestParam Integer learningOutcomeId,
            RedirectAttributes redirectAttributes) {
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        Optional<LearningOutcome> learningOutcomeOpt = learningOutcomeService.getLearningOutcomeById(learningOutcomeId);
        
        if (courseOpt.isPresent() && assessmentOpt.isPresent() && learningOutcomeOpt.isPresent()) {
            AssessmentToolId id = new AssessmentToolId(assessmentId, courseId, learningOutcomeId);
            Optional<AssessmentTool> toolOpt = assessmentToolService.getAssessmentToolById(id);
            
            if (toolOpt.isPresent()) {
                assessmentToolService.deleteAssessmentTool(toolOpt.get());
                redirectAttributes.addFlashAttribute("success", "Assessment tool deleted successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "Assessment tool not found");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Could not delete assessment tool - missing required data");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    */
    
    @PostMapping("/{courseId}/asiin-assessment-tool")
    public String addAsiinAssessmentTool(
            @PathVariable String courseId,
            @RequestParam Integer assessmentId,
            @RequestParam String cloId,
            @RequestParam Integer percentage,
            RedirectAttributes redirectAttributes) {
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        Optional<AsiinCLO> cloOpt = asiinCLOService.getAsiinCLOById(cloId);
        
        if (courseOpt.isPresent() && assessmentOpt.isPresent() && cloOpt.isPresent()) {
            AsiinAssessmentTool asiinAssessmentTool = new AsiinAssessmentTool();
            asiinAssessmentTool.setCloId(cloId);
            asiinAssessmentTool.setCourse(courseOpt.get());
            asiinAssessmentTool.setAssessment(assessmentOpt.get());
            asiinAssessmentTool.setPercentage(percentage);
            
            asiinAssessmentToolService.saveAsiinAssessmentTool(asiinAssessmentTool);
            redirectAttributes.addFlashAttribute("success", "ASIIN assessment tool added successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Could not add ASIIN assessment tool - missing required data");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/asiin-assessment-tool/update")
    public String updateAsiinAssessmentTool(
            @PathVariable String courseId,
            @RequestParam Integer assessmentId,
            @RequestParam String cloId,
            @RequestParam Integer percentage,
            RedirectAttributes redirectAttributes) {
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        Optional<AsiinCLO> cloOpt = asiinCLOService.getAsiinCLOById(cloId);
        
        if (courseOpt.isPresent() && assessmentOpt.isPresent() && cloOpt.isPresent()) {
            AsiinAssessmentToolId id = new AsiinAssessmentToolId(cloId, courseId, assessmentId);
            Optional<AsiinAssessmentTool> toolOpt = asiinAssessmentToolService.getAsiinAssessmentToolById(id);
            
            if (toolOpt.isPresent()) {
                AsiinAssessmentTool asiinAssessmentTool = toolOpt.get();
                asiinAssessmentTool.setPercentage(percentage);
                
                asiinAssessmentToolService.saveAsiinAssessmentTool(asiinAssessmentTool);
                redirectAttributes.addFlashAttribute("success", "ASIIN assessment tool updated successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "ASIIN assessment tool not found");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Could not update ASIIN assessment tool - missing required data");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @GetMapping("/{courseId}/asiin-assessment-tool/delete")
    public String deleteAsiinAssessmentTool(
            @PathVariable String courseId,
            @RequestParam Integer assessmentId,
            @RequestParam String cloId,
            RedirectAttributes redirectAttributes) {
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        Optional<AsiinCLO> cloOpt = asiinCLOService.getAsiinCLOById(cloId);
        
        if (courseOpt.isPresent() && assessmentOpt.isPresent() && cloOpt.isPresent()) {
            AsiinAssessmentToolId id = new AsiinAssessmentToolId(cloId, courseId, assessmentId);
            Optional<AsiinAssessmentTool> toolOpt = asiinAssessmentToolService.getAsiinAssessmentToolById(id);
            
            if (toolOpt.isPresent()) {
                asiinAssessmentToolService.deleteAsiinAssessmentTool(toolOpt.get());
                redirectAttributes.addFlashAttribute("success", "ASIIN assessment tool deleted successfully");
            } else {
                redirectAttributes.addFlashAttribute("error", "ASIIN assessment tool not found");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Could not delete ASIIN assessment tool - missing required data");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @GetMapping("/{courseId}/asiin-assessment-tool/edit")
    public String showEditAsiinAssessmentToolForm(
            @PathVariable String courseId,
            @RequestParam Integer assessmentId,
            @RequestParam String cloId,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        Optional<AsiinCLO> cloOpt = asiinCLOService.getAsiinCLOById(cloId);
        
        if (courseOpt.isPresent() && assessmentOpt.isPresent() && cloOpt.isPresent()) {
            AsiinAssessmentToolId id = new AsiinAssessmentToolId(cloId, courseId, assessmentId);
            Optional<AsiinAssessmentTool> toolOpt = asiinAssessmentToolService.getAsiinAssessmentToolById(id);
            
            if (toolOpt.isPresent()) {
                model.addAttribute("course", courseOpt.get());
                model.addAttribute("assessment", assessmentOpt.get());
                model.addAttribute("clo", cloOpt.get());
                model.addAttribute("asiinAssessmentTool", toolOpt.get());
                model.addAttribute("allAssessments", assessmentService.getAllAssessments());
                model.addAttribute("asiinCLOs", asiinCLOService.getAllAsiinCLOs());
                
                return "syllabus/editAsiinAssessmentTool";
            } else {
                redirectAttributes.addFlashAttribute("error", "ASIIN assessment tool not found");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Could not find required data for editing ASIIN assessment tool");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/asiin-assessment-tool/edit")
    public String processEditAsiinAssessmentTool(
            @PathVariable String courseId,
            @RequestParam Integer originalAssessmentId,
            @RequestParam String originalCloId,
            @RequestParam(required = false) Integer newAssessmentId,
            @RequestParam(required = false) String newCloId,
            @RequestParam Integer percentage,
            RedirectAttributes redirectAttributes) {
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        
        if (courseOpt.isPresent()) {
            // Delete the original assessment tool
            AsiinAssessmentToolId originalId = new AsiinAssessmentToolId(originalCloId, courseId, originalAssessmentId);
            Optional<AsiinAssessmentTool> originalToolOpt = asiinAssessmentToolService.getAsiinAssessmentToolById(originalId);
            
            if (originalToolOpt.isPresent()) {
                // If assessment or CLO changed, delete old and create new
                if ((newAssessmentId != null && !newAssessmentId.equals(originalAssessmentId)) || 
                    (newCloId != null && !newCloId.equals(originalCloId))) {
                    
                    // Delete original
                    asiinAssessmentToolService.deleteAsiinAssessmentTool(originalToolOpt.get());
                    
                    // Create new with updated values
                    int assessmentIdToUse = (newAssessmentId != null) ? newAssessmentId : originalAssessmentId;
                    String cloIdToUse = (newCloId != null) ? newCloId : originalCloId;
                    
                    Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentIdToUse);
                    Optional<AsiinCLO> cloOpt = asiinCLOService.getAsiinCLOById(cloIdToUse);
                    
                    if (assessmentOpt.isPresent() && cloOpt.isPresent()) {
                        AsiinAssessmentTool newTool = new AsiinAssessmentTool();
                        newTool.setCourse(courseOpt.get());
                        newTool.setAssessment(assessmentOpt.get());
                        newTool.setCloId(cloIdToUse);
                        newTool.setPercentage(percentage);
                        
                        asiinAssessmentToolService.saveAsiinAssessmentTool(newTool);
                        redirectAttributes.addFlashAttribute("success", "ASIIN assessment tool updated successfully");
                    } else {
                        redirectAttributes.addFlashAttribute("error", "Could not update ASIIN assessment tool - invalid assessment or CLO");
                    }
                } else {
                    // Only percentage changed, update existing
                    AsiinAssessmentTool tool = originalToolOpt.get();
                    tool.setPercentage(percentage);
                    asiinAssessmentToolService.saveAsiinAssessmentTool(tool);
                    redirectAttributes.addFlashAttribute("success", "ASIIN assessment tool updated successfully");
                }
            } else {
                redirectAttributes.addFlashAttribute("error", "ASIIN assessment tool not found");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Course not found");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
}