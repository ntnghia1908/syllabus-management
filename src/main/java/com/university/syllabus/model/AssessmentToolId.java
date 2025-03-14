// AssessmentToolId.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentToolId implements Serializable {
    private Integer assessment;
    private String course;
    private Integer learningOutcome;
}