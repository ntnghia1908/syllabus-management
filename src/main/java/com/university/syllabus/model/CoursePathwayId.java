package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursePathwayId implements Serializable {
    private Integer program;
    private Integer pathway;
    private String course;
} 