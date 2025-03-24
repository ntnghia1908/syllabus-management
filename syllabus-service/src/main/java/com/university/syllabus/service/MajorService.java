// MajorService.java
package com.university.syllabus.service;

import com.university.syllabus.model.Major;
import com.university.syllabus.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;
    
    public List<Major> getAllMajors() {
        return majorRepository.findAll();
    }
    
    public Optional<Major> getMajorById(Integer id) {
        return majorRepository.findById(id);
    }
    
    public List<Major> getMajorsByDiscipline(Integer disciplineId) {
        return majorRepository.findByDisciplineId(disciplineId);
    }
    
    public Major saveMajor(Major major) {
        return majorRepository.save(major);
    }
    
    public void deleteMajor(Integer id) {
        majorRepository.deleteById(id);
    }
}