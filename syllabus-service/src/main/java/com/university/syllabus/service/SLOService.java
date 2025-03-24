// SLOService.java
package com.university.syllabus.service;

import com.university.syllabus.model.SLO;
import com.university.syllabus.repository.SLORepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SLOService {
    private final SLORepository sloRepository;
    
    public List<SLO> getAllSLOs() {
        return sloRepository.findAll();
    }
    
    public Optional<SLO> getSLOById(Integer id) {
        return sloRepository.findById(id);
    }
    
    public List<SLO> getSLOsByCriteria(Integer criteria) {
        return sloRepository.findByCriteria(criteria);
    }
    
    public SLO saveSLO(SLO slo) {
        return sloRepository.save(slo);
    }
    
    public void deleteSLO(Integer id) {
        sloRepository.deleteById(id);
    }
}