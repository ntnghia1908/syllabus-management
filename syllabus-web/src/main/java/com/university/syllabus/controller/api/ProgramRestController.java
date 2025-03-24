package com.university.syllabus.controller.api;

import com.university.syllabus.dto.ProgramDTO;
import com.university.syllabus.model.CoursePathway;
import com.university.syllabus.model.Program;
import com.university.syllabus.service.CoursePathwayService;
import com.university.syllabus.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/programs")
@RequiredArgsConstructor
public class ProgramRestController {
    
    private final ProgramService programService;
    private final CoursePathwayService coursePathwayService;
    
    @GetMapping
    public ResponseEntity<List<ProgramDTO>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        return ResponseEntity.ok(ProgramDTO.fromEntities(programs));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProgramDTO> getProgramById(@PathVariable Integer id) {
        Optional<Program> programOpt = programService.getProgramById(id);
        return programOpt.map(program -> ResponseEntity.ok(ProgramDTO.fromEntity(program)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ProgramDTO> createProgram(@RequestBody ProgramDTO programDTO) {
        Program program = programDTO.toEntity();
        Program savedProgram = programService.saveProgram(program);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProgramDTO.fromEntity(savedProgram));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProgramDTO> updateProgram(@PathVariable Integer id, @RequestBody ProgramDTO programDTO) {
        if (!id.equals(programDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Program> programOpt = programService.getProgramById(id);
        if (programOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Program program = programDTO.toEntity();
        Program updatedProgram = programService.saveProgram(program);
        return ResponseEntity.ok(ProgramDTO.fromEntity(updatedProgram));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProgram(@PathVariable Integer id) {
        Optional<Program> programOpt = programService.getProgramById(id);
        if (programOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Check for assigned courses
        List<CoursePathway> assignedCourses = coursePathwayService.getCoursePathwaysByProgram(id);
        if (!assignedCourses.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Cannot delete program because it has " + assignedCourses.size() + 
                          " course(s) assigned to it. Please remove these courses first.");
        }
        
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/versions")
    public ResponseEntity<List<String>> getAllVersions() {
        List<String> versions = programService.getAllVersions();
        return ResponseEntity.ok(versions);
    }
    
    @GetMapping("/{id}/curriculum")
    public ResponseEntity<List<CoursePathway>> getProgramCurriculum(@PathVariable Integer id) {
        Optional<Program> programOpt = programService.getProgramById(id);
        if (programOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        List<CoursePathway> curriculum = coursePathwayService.getCoursePathwaysByProgramOrderedBySemesterAndYear(id);
        return ResponseEntity.ok(curriculum);
    }
} 