// CLOSLOId.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CLOSLOId implements Serializable {
    private Integer slo;
    private Integer learningOutcome;
}