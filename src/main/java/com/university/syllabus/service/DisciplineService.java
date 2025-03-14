// DisciplineService.java
package com.university.syllabus.service;

import com.university.syllabus.model.Discipline;
import com.university.syllabus.repository.DisciplineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;
    
    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }
    
    public Optional<Discipline> getDisciplineById(Integer id) {
        return disciplineRepository.findById(id);
    }
    
    public Discipline saveDiscipline(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }
    
    public void deleteDiscipline(Integer id) {
        disciplineRepository.deleteById(id);
    }
}