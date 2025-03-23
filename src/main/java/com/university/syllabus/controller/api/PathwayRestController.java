package com.university.syllabus.controller.api;

import com.university.syllabus.dto.PathwayDTO;
import com.university.syllabus.model.CoursePathway;
import com.university.syllabus.model.Pathway;
import com.university.syllabus.service.CoursePathwayService;
import com.university.syllabus.service.PathwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pathways")
@RequiredArgsConstructor
public class PathwayRestController {
    
    private final PathwayService pathwayService;
    private final CoursePathwayService coursePathwayService;
    
    @GetMapping
    public ResponseEntity<List<PathwayDTO>> getAllPathways() {
        List<Pathway> pathways = pathwayService.getAllPathways();
        return ResponseEntity.ok(PathwayDTO.fromEntities(pathways));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PathwayDTO> getPathwayById(@PathVariable Integer id) {
        Optional<Pathway> pathwayOpt = pathwayService.getPathwayById(id);
        return pathwayOpt.map(pathway -> ResponseEntity.ok(PathwayDTO.fromEntity(pathway)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<PathwayDTO> createPathway(@RequestBody PathwayDTO pathwayDTO) {
        Pathway pathway = pathwayDTO.toEntity();
        Pathway savedPathway = pathwayService.savePathway(pathway);
        return ResponseEntity.status(HttpStatus.CREATED).body(PathwayDTO.fromEntity(savedPathway));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PathwayDTO> updatePathway(@PathVariable Integer id, @RequestBody PathwayDTO pathwayDTO) {
        if (!id.equals(pathwayDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Pathway> pathwayOpt = pathwayService.getPathwayById(id);
        if (pathwayOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Pathway pathway = pathwayDTO.toEntity();
        Pathway updatedPathway = pathwayService.savePathway(pathway);
        return ResponseEntity.ok(PathwayDTO.fromEntity(updatedPathway));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePathway(@PathVariable Integer id) {
        Optional<Pathway> pathwayOpt = pathwayService.getPathwayById(id);
        if (pathwayOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        List<CoursePathway> assignedCourses = coursePathwayService.getCoursePathwaysByPathway(id);
        if (!assignedCourses.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Cannot delete pathway because it has " + assignedCourses.size() + 
                          " course(s) assigned to it. Please reassign these courses first.");
        }
        
        pathwayService.deletePathway(id);
        return ResponseEntity.noContent().build();
    }
} 