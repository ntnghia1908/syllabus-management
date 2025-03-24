package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsiinAssessmentToolId implements Serializable {
    private String cloId;
    private String course;
    private Integer assessment;
} 