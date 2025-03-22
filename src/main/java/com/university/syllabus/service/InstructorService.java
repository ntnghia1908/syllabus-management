// InstructorService.java
package com.university.syllabus.service;

import com.university.syllabus.model.Instructor;
import com.university.syllabus.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;
    
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }
    
    public Optional<Instructor> getInstructorById(String id) {
        return instructorRepository.findById(id);
    }
    
    public List<Instructor> searchInstructors(String query) {
        return instructorRepository.searchInstructors(query);
    }
    
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }
    
    public void deleteInstructor(String id) {
        instructorRepository.deleteById(id);
    }
}