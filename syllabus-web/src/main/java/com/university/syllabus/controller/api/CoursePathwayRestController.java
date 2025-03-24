package com.university.syllabus.controller.api;

import com.university.syllabus.dto.CoursePathwayDTO;
import com.university.syllabus.model.CoursePathway;
import com.university.syllabus.model.CoursePathwayId;
import com.university.syllabus.service.CoursePathwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course-pathways")
@RequiredArgsConstructor
public class CoursePathwayRestController {
    
    private final CoursePathwayService coursePathwayService;
    
    @GetMapping("/program/{programId}")
    public ResponseEntity<List<CoursePathwayDTO>> getCoursePathwaysByProgram(@PathVariable Integer programId) {
        List<CoursePathway> coursePathways = coursePathwayService.getCoursePathwaysByProgram(programId);
        return ResponseEntity.ok(CoursePathwayDTO.fromEntities(coursePathways));
    }
    
    @GetMapping("/program/{programId}/ordered")
    public ResponseEntity<List<CoursePathwayDTO>> getCoursePathwaysByProgramOrdered(@PathVariable Integer programId) {
        List<CoursePathway> coursePathways = coursePathwayService.getCoursePathwaysByProgramOrderedBySemesterAndYear(programId);
        return ResponseEntity.ok(CoursePathwayDTO.fromEntities(coursePathways));
    }
    
    @GetMapping("/pathway/{pathwayId}")
    public ResponseEntity<List<CoursePathwayDTO>> getCoursePathwaysByPathway(@PathVariable Integer pathwayId) {
        List<CoursePathway> coursePathways = coursePathwayService.getCoursePathwaysByPathway(pathwayId);
        return ResponseEntity.ok(CoursePathwayDTO.fromEntities(coursePathways));
    }
    
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CoursePathwayDTO>> getCoursePathwaysByCourse(@PathVariable String courseId) {
        List<CoursePathway> coursePathways = coursePathwayService.getCoursePathwaysByCourse(courseId);
        return ResponseEntity.ok(CoursePathwayDTO.fromEntities(coursePathways));
    }
    
    @GetMapping("/{programId}/{pathwayId}/{courseId}")
    public ResponseEntity<CoursePathwayDTO> getCoursePathwayById(
            @PathVariable Integer programId,
            @PathVariable Integer pathwayId,
            @PathVariable String courseId) {
        CoursePathwayId id = new CoursePathwayId(programId, pathwayId, courseId);
        Optional<CoursePathway> coursePathwayOpt = coursePathwayService.getCoursePathwayById(id);
        return coursePathwayOpt.map(coursePathway -> ResponseEntity.ok(CoursePathwayDTO.fromEntity(coursePathway)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<CoursePathwayDTO> createCoursePathway(@RequestBody CoursePathwayDTO coursePathwayDTO) {
        CoursePathway coursePathway = coursePathwayDTO.toEntity();
        CoursePathway savedCoursePathway = coursePathwayService.saveCoursePathway(coursePathway);
        return ResponseEntity.status(HttpStatus.CREATED).body(CoursePathwayDTO.fromEntity(savedCoursePathway));
    }
    
    @PutMapping("/{programId}/{pathwayId}/{courseId}")
    public ResponseEntity<CoursePathwayDTO> updateCoursePathway(
            @PathVariable Integer programId,
            @PathVariable Integer pathwayId,
            @PathVariable String courseId,
            @RequestBody CoursePathwayDTO coursePathwayDTO) {
        
        // Verify that the path variables match the DTO
        if (!programId.equals(coursePathwayDTO.getProgramId()) ||
            !pathwayId.equals(coursePathwayDTO.getPathwayId()) ||
            !courseId.equals(coursePathwayDTO.getCourseId())) {
            return ResponseEntity.badRequest().build();
        }
        
        CoursePathwayId id = new CoursePathwayId(programId, pathwayId, courseId);
        Optional<CoursePathway> coursePathwayOpt = coursePathwayService.getCoursePathwayById(id);
        if (coursePathwayOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        CoursePathway coursePathway = coursePathwayDTO.toEntity();
        CoursePathway updatedCoursePathway = coursePathwayService.saveCoursePathway(coursePathway);
        return ResponseEntity.ok(CoursePathwayDTO.fromEntity(updatedCoursePathway));
    }
    
    @DeleteMapping("/{programId}/{pathwayId}/{courseId}")
    public ResponseEntity<Void> deleteCoursePathway(
            @PathVariable Integer programId,
            @PathVariable Integer pathwayId,
            @PathVariable String courseId) {
        
        CoursePathwayId id = new CoursePathwayId(programId, pathwayId, courseId);
        Optional<CoursePathway> coursePathwayOpt = coursePathwayService.getCoursePathwayById(id);
        if (coursePathwayOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        coursePathwayService.deleteCoursePathway(id);
        return ResponseEntity.noContent().build();
    }
} 