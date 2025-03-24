// CLOSLOService.java
package com.university.syllabus.service;

import com.university.syllabus.model.CLOSLO;
import com.university.syllabus.model.CLOSLOId;
import com.university.syllabus.repository.CLOSLORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CLOSLOService {
    private final CLOSLORepository closloRepository;
    
    public List<CLOSLO> getAllCLOSLOs() {
        return closloRepository.findAll();
    }
    
    public Optional<CLOSLO> getCLOSLOById(CLOSLOId id) {
        return closloRepository.findById(id);
    }
    
    public List<CLOSLO> getCLOSLOsByLearningOutcomeId(Integer learningOutcomeId) {
        return closloRepository.findByLearningOutcomeId(learningOutcomeId);
    }
    
    public List<CLOSLO> getCLOSLOsBySloId(Integer sloId) {
        return closloRepository.findBySloId(sloId);
    }
    
    public CLOSLO saveCLOSLO(CLOSLO closlo) {
        return closloRepository.save(closlo);
    }
    
    public void deleteCLOSLO(CLOSLOId id) {
        closloRepository.deleteById(id);
    }
}