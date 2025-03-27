package com.university.syllabus.service;

import com.university.syllabus.model.AsiinCLO;
import com.university.syllabus.repository.AsiinCLORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsiinCLOService {
    private final AsiinCLORepository asiinCLORepository;

    public AsiinCLOService(AsiinCLORepository asiinCLORepository) {
        this.asiinCLORepository = asiinCLORepository;
    }

    public List<AsiinCLO> getAllAsiinCLOs() {
        return asiinCLORepository.findAll();
    }

    public Optional<AsiinCLO> getAsiinCLOById(String id) {
        return asiinCLORepository.findById(id);
    }

    public AsiinCLO saveAsiinCLO(AsiinCLO asiinCLO) {
        return asiinCLORepository.save(asiinCLO);
    }

    public void deleteAsiinCLO(AsiinCLO asiinCLO) {
        asiinCLORepository.delete(asiinCLO);
    }
} 