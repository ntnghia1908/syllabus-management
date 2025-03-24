// StudentService.java
package com.university.syllabus.service;

import com.university.syllabus.model.Student;
import com.university.syllabus.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }
    
    public List<Student> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major);
    }
    
    public List<Student> getStudentsByBatch(Integer batch) {
        return studentRepository.findByBatch(batch);
    }
    
    public List<Student> searchStudents(String query) {
        return studentRepository.searchStudents(query);
    }
    
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}