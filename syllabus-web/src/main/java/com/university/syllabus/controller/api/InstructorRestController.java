package com.university.syllabus.controller.api;

import com.university.syllabus.dto.InstructorDTO;
import com.university.syllabus.model.Instructor;
import com.university.syllabus.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorRestController {
    
    private final InstructorService instructorService;
    
    @GetMapping
    public ResponseEntity<List<InstructorDTO>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(InstructorDTO.fromEntities(instructors));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDTO> getInstructorById(@PathVariable String id) {
        Optional<Instructor> instructorOpt = instructorService.getInstructorById(id);
        return instructorOpt.map(instructor -> ResponseEntity.ok(InstructorDTO.fromEntity(instructor)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<InstructorDTO> createInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = instructorDTO.toEntity();
        Instructor savedInstructor = instructorService.saveInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(InstructorDTO.fromEntity(savedInstructor));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@PathVariable String id, @RequestBody InstructorDTO instructorDTO) {
        if (!id.equals(instructorDTO.getId())) {
            return ResponseEntity.badRequest().build();
        }
        
        Optional<Instructor> instructorOpt = instructorService.getInstructorById(id);
        if (instructorOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Instructor instructor = instructorDTO.toEntity();
        Instructor updatedInstructor = instructorService.saveInstructor(instructor);
        return ResponseEntity.ok(InstructorDTO.fromEntity(updatedInstructor));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable String id) {
        Optional<Instructor> instructorOpt = instructorService.getInstructorById(id);
        if (instructorOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<InstructorDTO>> searchInstructors(@RequestParam String query) {
        List<Instructor> instructors = instructorService.searchInstructors(query);
        return ResponseEntity.ok(InstructorDTO.fromEntities(instructors));
    }
} 