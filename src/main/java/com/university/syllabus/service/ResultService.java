// ResultService.java
package com.university.syllabus.service;

import com.university.syllabus.model.Result;
import com.university.syllabus.model.ResultId;
import com.university.syllabus.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
    
    public Optional<Result> getResultById(ResultId id) {
        return resultRepository.findById(id);
    }
    
    public List<Result> getResultsByStudentId(String studentId) {
        return resultRepository.findByStudentId(studentId);
    }
    
    public List<Result> getResultsByClassSessionId(String classSessionId) {
        return resultRepository.findByClassSessionId(classSessionId);
    }
    
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }
    
    public void deleteResult(ResultId id) {
        resultRepository.deleteById(id);
    }
}