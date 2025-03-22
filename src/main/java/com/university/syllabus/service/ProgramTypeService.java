// ProgramTypeService.java
package com.university.syllabus.service;

import com.university.syllabus.model.ProgramType;
import com.university.syllabus.repository.ProgramTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgramTypeService {
    private final ProgramTypeRepository programTypeRepository;
    
    public List<ProgramType> getAllProgramTypes() {
        return programTypeRepository.findAll();
    }
    
    public Optional<ProgramType> getProgramTypeById(Integer id) {
        return programTypeRepository.findById(id);
    }
    
    public ProgramType saveProgramType(ProgramType programType) {
        return programTypeRepository.save(programType);
    }
    
    public void deleteProgramType(Integer id) {
        programTypeRepository.deleteById(id);
    }
} 