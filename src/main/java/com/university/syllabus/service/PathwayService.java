package com.university.syllabus.service;

import com.university.syllabus.model.Pathway;
import com.university.syllabus.repository.PathwayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PathwayService {
    private final PathwayRepository pathwayRepository;
    
    public List<Pathway> getAllPathways() {
        return pathwayRepository.findAll();
    }
    
    public Optional<Pathway> getPathwayById(Integer id) {
        return pathwayRepository.findById(id);
    }
    
    public Pathway savePathway(Pathway pathway) {
        return pathwayRepository.save(pathway);
    }
    
    public void deletePathway(Integer id) {
        pathwayRepository.deleteById(id);
    }
} 