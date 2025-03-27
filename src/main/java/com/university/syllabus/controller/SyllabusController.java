// SyllabusController.java
package com.university.syllabus.controller;

import com.university.syllabus.dto.CourseBookDTO;
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
    
    @Autowired
    private CourseBookService courseBookService;
    
    @Autowired
    private BookService bookService;
    
    @ModelAttribute("bookService")
    public BookService getBookService() {
        return bookService;
    }
    
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
            
            // Get ASIIN CLOs filtered by course ID
            List<AsiinCLO> allAsiinClos = asiinCLOService.getAllAsiinCLOs();
            List<AsiinCLO> filteredAsiinClos = allAsiinClos.stream()
                .filter(clo -> clo.getId().startsWith(courseId + "_"))
                .toList();
            
            // Get course books
            List<CourseBookDTO> courseBooks = courseBookService.getBooksForCourse(courseId);
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            // Assessment Tool feature disabled
            // model.addAttribute("assessmentTools", assessmentTools);
            model.addAttribute("asiinAssessmentTools", asiinAssessmentTools);
            model.addAttribute("asiinClos", filteredAsiinClos);
            model.addAttribute("courseBooks", courseBooks);
            
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
                
            // Get ASIIN CLOs filtered by course ID
            List<AsiinCLO> allAsiinCLOs = asiinCLOService.getAllAsiinCLOs();
            List<AsiinCLO> filteredAsiinCLOs = allAsiinCLOs.stream()
                .filter(clo -> clo.getId().startsWith(courseId + "_"))
                .toList();
            
            // Get course books and all available books
            List<CourseBookDTO> courseBooks = courseBookService.getBooksForCourse(courseId);
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            model.addAttribute("allAssessments", allAssessments);
            // Assessment Tool feature disabled
            // model.addAttribute("assessmentTools", assessmentTools);
            model.addAttribute("asiinAssessmentTools", asiinAssessmentTools);
            model.addAttribute("asiinCLOs", filteredAsiinCLOs);
            model.addAttribute("courseBooks", courseBooks);
            model.addAttribute("bookService", bookService);
            
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
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        if (assessmentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Assessment with ID " + assessmentId + " not found. It may have been deleted.");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Course not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        // Check if this assessment is already associated with the course
        List<CourseAssessment> existingAssessments = courseAssessmentService.getCourseAssessmentsByCourse(courseId);
        boolean alreadyExists = existingAssessments.stream()
            .anyMatch(ca -> ca.getAssessment().getId().equals(assessmentId));
            
        if (alreadyExists) {
            redirectAttributes.addFlashAttribute("error", "This assessment is already associated with the course");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        CourseAssessment courseAssessment = new CourseAssessment();
        courseAssessment.setCourse(courseOpt.get());
        courseAssessment.setAssessment(assessmentOpt.get());
        courseAssessment.setPercentage(percentage);
        
        courseAssessmentService.saveCourseAssessment(courseAssessment);
        redirectAttributes.addFlashAttribute("success", "Assessment added successfully");
        
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
            
            // Get ASIIN CLOs filtered by course ID
            List<AsiinCLO> allAsiinClos = asiinCLOService.getAllAsiinCLOs();
            List<AsiinCLO> filteredAsiinClos = allAsiinClos.stream()
                .filter(clo -> clo.getId().startsWith(courseId + "_"))
                .toList();
            
            // Get course books
            List<CourseBookDTO> courseBooks = courseBookService.getBooksForCourse(courseId);
            
            model.addAttribute("course", course);
            model.addAttribute("learningOutcomes", learningOutcomes);
            model.addAttribute("courseAssessments", courseAssessments);
            // Assessment Tool feature disabled
            // model.addAttribute("assessmentTools", assessmentTools);
            model.addAttribute("asiinAssessmentTools", asiinAssessmentTools);
            model.addAttribute("asiinClos", filteredAsiinClos);
            model.addAttribute("courseBooks", courseBooks);
            model.addAttribute("exportDate", java.time.LocalDateTime.now());
            
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
        
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        if (assessmentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Assessment with ID " + assessmentId + " not found. It may have been deleted.");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Course not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<AsiinCLO> cloOpt = asiinCLOService.getAsiinCLOById(cloId);
        if (cloOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "ASIIN CLO with ID " + cloId + " not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        // Check if this combination already exists
        AsiinAssessmentToolId id = new AsiinAssessmentToolId(cloId, courseId, assessmentId);
        Optional<AsiinAssessmentTool> existingTool = asiinAssessmentToolService.getAsiinAssessmentToolById(id);
        if (existingTool.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "This assessment tool combination already exists");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        AsiinAssessmentTool asiinAssessmentTool = new AsiinAssessmentTool();
        asiinAssessmentTool.setCloId(cloId);
        asiinAssessmentTool.setCourse(courseOpt.get());
        asiinAssessmentTool.setAssessment(assessmentOpt.get());
        asiinAssessmentTool.setPercentage(percentage);
        
        asiinAssessmentToolService.saveAsiinAssessmentTool(asiinAssessmentTool);
        redirectAttributes.addFlashAttribute("success", "ASIIN assessment tool added successfully");
        
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
        
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        if (assessmentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Assessment with ID " + assessmentId + " not found. It may have been deleted already.");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Course not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<AsiinCLO> cloOpt = asiinCLOService.getAsiinCLOById(cloId);
        if (cloOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "ASIIN CLO with ID " + cloId + " not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        AsiinAssessmentToolId id = new AsiinAssessmentToolId(cloId, courseId, assessmentId);
        Optional<AsiinAssessmentTool> toolOpt = asiinAssessmentToolService.getAsiinAssessmentToolById(id);
        
        if (toolOpt.isPresent()) {
            asiinAssessmentToolService.deleteAsiinAssessmentTool(toolOpt.get());
            redirectAttributes.addFlashAttribute("success", "ASIIN assessment tool deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "ASIIN assessment tool not found");
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
        
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(assessmentId);
        if (assessmentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Assessment with ID " + assessmentId + " not found. It may have been deleted.");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Course not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<AsiinCLO> cloOpt = asiinCLOService.getAsiinCLOById(cloId);
        if (cloOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "ASIIN CLO with ID " + cloId + " not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Course course = courseOpt.get();
        Assessment assessment = assessmentOpt.get();
        AsiinCLO clo = cloOpt.get();
        
        AsiinAssessmentToolId id = new AsiinAssessmentToolId(cloId, courseId, assessmentId);
        Optional<AsiinAssessmentTool> toolOpt = asiinAssessmentToolService.getAsiinAssessmentToolById(id);
        
        if (toolOpt.isPresent()) {
            model.addAttribute("course", course);
            model.addAttribute("assessment", assessment);
            model.addAttribute("clo", clo);
            model.addAttribute("asiinAssessmentTool", toolOpt.get());
            model.addAttribute("allAssessments", assessmentService.getAllAssessments());
            model.addAttribute("asiinCLOs", asiinCLOService.getAllAsiinCLOs());
            
            return "syllabus/editAsiinAssessmentTool";
        } else {
            redirectAttributes.addFlashAttribute("error", "ASIIN assessment tool not found");
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
    
    @GetMapping("/{courseId}/learning-outcome/{id}/edit")
    public String showEditLearningOutcomeForm(@PathVariable String courseId, @PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<LearningOutcome> learningOutcomeOpt = learningOutcomeService.getLearningOutcomeById(id);
        
        if (learningOutcomeOpt.isPresent()) {
            LearningOutcome learningOutcome = learningOutcomeOpt.get();
            // Verify that the learning outcome belongs to the specified course
            if (!learningOutcome.getCourse().getId().equals(courseId)) {
                redirectAttributes.addFlashAttribute("error", "Learning outcome does not belong to this course");
                return "redirect:/syllabus/" + courseId + "/edit";
            }
            
            model.addAttribute("learningOutcome", learningOutcome);
            model.addAttribute("course", learningOutcome.getCourse());
            return "syllabus/editLearningOutcome";
        }
        
        redirectAttributes.addFlashAttribute("error", "Learning outcome not found");
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/learning-outcome/{id}/edit")
    public String updateLearningOutcome(@PathVariable String courseId, @PathVariable Integer id, 
                                       @ModelAttribute LearningOutcome learningOutcome,
                                       RedirectAttributes redirectAttributes) {
        Optional<LearningOutcome> existingOutcomeOpt = learningOutcomeService.getLearningOutcomeById(id);
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        
        if (existingOutcomeOpt.isPresent() && courseOpt.isPresent()) {
            LearningOutcome existingOutcome = existingOutcomeOpt.get();
            // Verify that the learning outcome belongs to the specified course
            if (!existingOutcome.getCourse().getId().equals(courseId)) {
                redirectAttributes.addFlashAttribute("error", "Learning outcome does not belong to this course");
                return "redirect:/syllabus/" + courseId + "/edit";
            }
            
            // Update only the fields that can be edited, preserving the course and ID
            existingOutcome.setDescription(learningOutcome.getDescription());
            existingOutcome.setDescriptionVn(learningOutcome.getDescriptionVn());
            
            learningOutcomeService.saveLearningOutcome(existingOutcome);
            redirectAttributes.addFlashAttribute("success", "Learning outcome updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Learning outcome or course not found");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @GetMapping("/{courseId}/learning-outcome/{id}/delete")
    public String deleteLearningOutcome(@PathVariable String courseId, @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<LearningOutcome> learningOutcomeOpt = learningOutcomeService.getLearningOutcomeById(id);
        
        if (learningOutcomeOpt.isPresent()) {
            LearningOutcome learningOutcome = learningOutcomeOpt.get();
            // Verify that the learning outcome belongs to the specified course
            if (!learningOutcome.getCourse().getId().equals(courseId)) {
                redirectAttributes.addFlashAttribute("error", "Learning outcome does not belong to this course");
                return "redirect:/syllabus/" + courseId + "/edit";
            }
            
            learningOutcomeService.deleteLearningOutcome(id);
            redirectAttributes.addFlashAttribute("success", "Learning outcome deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Learning outcome not found");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @GetMapping("/{courseId}/assessment/{id}/edit")
    public String showEditAssessmentForm(@PathVariable String courseId, @PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(id);
        
        if (assessmentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Assessment with ID " + id + " not found. It may have been deleted.");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            
            // Find the course assessment
            List<CourseAssessment> courseAssessments = courseAssessmentService.getCourseAssessmentsByCourse(courseId);
            Optional<CourseAssessment> courseAssessmentOpt = courseAssessments.stream()
                .filter(ca -> ca.getAssessment().getId().equals(id))
                .findFirst();
            
            if (courseAssessmentOpt.isPresent()) {
                CourseAssessment courseAssessment = courseAssessmentOpt.get();
                model.addAttribute("courseAssessment", courseAssessment);
                model.addAttribute("course", course);
                model.addAttribute("allAssessments", assessmentService.getAllAssessments());
                return "syllabus/editAssessment";
            } else {
                redirectAttributes.addFlashAttribute("error", "Course assessment not found");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Course not found");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/assessment/{id}/edit")
    public String updateAssessment(@PathVariable String courseId, @PathVariable Integer id, 
                                  @RequestParam Integer assessmentId,
                                  @RequestParam Integer percentage,
                                  RedirectAttributes redirectAttributes) {
        Optional<Assessment> originalAssessmentOpt = assessmentService.getAssessmentById(id);
        if (originalAssessmentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Original assessment with ID " + id + " not found. It may have been deleted.");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<Assessment> newAssessmentOpt = assessmentService.getAssessmentById(assessmentId);
        if (newAssessmentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Selected assessment with ID " + assessmentId + " not found. It may have been deleted.");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Course not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Course course = courseOpt.get();
        Assessment newAssessment = newAssessmentOpt.get();
        
        // Find the course assessment
        List<CourseAssessment> courseAssessments = courseAssessmentService.getCourseAssessmentsByCourse(courseId);
        Optional<CourseAssessment> courseAssessmentOpt = courseAssessments.stream()
            .filter(ca -> ca.getAssessment().getId().equals(id))
            .findFirst();
        
        if (courseAssessmentOpt.isPresent()) {
            CourseAssessment courseAssessment = courseAssessmentOpt.get();
            
            // If assessment type changed, need to delete old one and create new one
            if (!id.equals(assessmentId)) {
                // Delete old assessment
                CourseAssessmentId courseAssessmentId = new CourseAssessmentId(id, courseId);
                courseAssessmentService.deleteCourseAssessment(courseAssessmentId);
                
                // Create new one
                CourseAssessment newCourseAssessment = new CourseAssessment();
                newCourseAssessment.setCourse(course);
                newCourseAssessment.setAssessment(newAssessment);
                newCourseAssessment.setPercentage(percentage);
                courseAssessmentService.saveCourseAssessment(newCourseAssessment);
            } else {
                // Just update percentage
                courseAssessment.setPercentage(percentage);
                courseAssessmentService.saveCourseAssessment(courseAssessment);
            }
            
            redirectAttributes.addFlashAttribute("success", "Assessment updated successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Course assessment not found");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @GetMapping("/{courseId}/assessment/{id}/delete")
    public String deleteAssessment(@PathVariable String courseId, @PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Assessment> assessmentOpt = assessmentService.getAssessmentById(id);
        if (assessmentOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Assessment with ID " + id + " not found. It may have been deleted already.");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        Optional<Course> courseOpt = courseService.getCourseById(courseId);
        if (courseOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Course not found");
            return "redirect:/syllabus/" + courseId + "/edit";
        }
        
        // Find the course assessment
        List<CourseAssessment> courseAssessments = courseAssessmentService.getCourseAssessmentsByCourse(courseId);
        Optional<CourseAssessment> courseAssessmentOpt = courseAssessments.stream()
            .filter(ca -> ca.getAssessment().getId().equals(id))
            .findFirst();
        
        if (courseAssessmentOpt.isPresent()) {
            CourseAssessmentId courseAssessmentId = new CourseAssessmentId(id, courseId);
            courseAssessmentService.deleteCourseAssessment(courseAssessmentId);
            redirectAttributes.addFlashAttribute("success", "Assessment deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Course assessment not found");
        }
        
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/book")
    public String addBookToCourse(@PathVariable String courseId, 
                                @RequestParam Long bookId,
                                @RequestParam String type,
                                RedirectAttributes redirectAttributes) {
        try {
            bookService.addBookToCourse(bookId, courseId, type);
            redirectAttributes.addFlashAttribute("success", "Book added to syllabus successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding book to syllabus: " + e.getMessage());
        }
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/book/{bookId}/remove")
    public String removeBookFromCourse(@PathVariable String courseId,
                                    @PathVariable Long bookId,
                                    RedirectAttributes redirectAttributes) {
        try {
            bookService.removeBookFromCourse(bookId, courseId);
            redirectAttributes.addFlashAttribute("success", "Book removed from syllabus successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error removing book from syllabus: " + e.getMessage());
        }
        return "redirect:/syllabus/" + courseId + "/edit";
    }
    
    @PostMapping("/{courseId}/book/{bookId}/update")
    public String updateBookType(@PathVariable String courseId,
                               @PathVariable Long bookId,
                               @RequestParam String type,
                               RedirectAttributes redirectAttributes) {
        try {
            bookService.updateBookCourseRelation(bookId, courseId, type);
            redirectAttributes.addFlashAttribute("success", "Book type updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating book type: " + e.getMessage());
        }
        return "redirect:/syllabus/" + courseId + "/edit";
    }
}