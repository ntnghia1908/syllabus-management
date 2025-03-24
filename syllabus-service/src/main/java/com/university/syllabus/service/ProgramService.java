// ProgramService.java
package com.university.syllabus.service;

import com.university.syllabus.model.Program;
import com.university.syllabus.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;
    
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }
    
    public Optional<Program> getProgramById(Integer id) {
        return programRepository.findById(id);
    }
    
    public List<Program> getProgramsByMajor(Integer majorId) {
        return programRepository.findByMajorId(majorId);
    }
    
    public List<String> getAllVersions() {
        return programRepository.findAllVersions();
    }
    
    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }
    
    public void deleteProgram(Integer id) {
        programRepository.deleteById(id);
    }
}