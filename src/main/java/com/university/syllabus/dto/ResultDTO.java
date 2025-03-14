// ResultDTO.java
package com.university.syllabus.dto;

import com.university.syllabus.model.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO {
    private String studentId;
    private String studentName;
    private Integer classSessionId;
    private String courseName;
    private Integer midScore;
    private Integer finalScore;
    private Integer inClassScore;
    private Integer GPA;
    private Integer abetScore;
    private Integer abet1;
    private Integer abet2;
    private Integer abet3;
    private Integer abet4;
    private Integer abet5;
    private Integer abet6;
    private Float avg;
    
    // Static method to convert Entity to DTO
    public static ResultDTO fromEntity(Result result) {
        ResultDTO dto = new ResultDTO();
        dto.setMidScore(result.getMidScore());
        dto.setFinalScore(result.getFinalScore());
        dto.setInClassScore(result.getInClassScore());
        dto.setGPA(result.getGPA());
        dto.setAbetScore(result.getAbetScore());
        dto.setAbet1(result.getAbet1());
        dto.setAbet2(result.getAbet2());
        dto.setAbet3(result.getAbet3());
        dto.setAbet4(result.getAbet4());
        dto.setAbet5(result.getAbet5());
        dto.setAbet6(result.getAbet6());
        dto.setAvg(result.getAvg());
        
        if (result.getStudent() != null) {
            dto.setStudentId(result.getStudent().getId());
            dto.setStudentName(result.getStudent().getName());
        }
        
        if (result.getClassSession() != null) {
            dto.setClassSessionId(result.getClassSession().getId());
            if (result.getClassSession().getCourse() != null) {
                dto.setCourseName(result.getClassSession().getCourse().getName());
            }
        }
        
        return dto;
    }
    
    // Static method to convert List of Entity to List of DTO
    public static List<ResultDTO> fromEntities(List<Result> results) {
        return results.stream().map(ResultDTO::fromEntity).collect(Collectors.toList());
    }
}