// CLO_SLO.java (Course Learning Outcome to Student Learning Outcome mapping)
package com.university.syllabus.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;


@Entity
@Table(name = "clo_slo")
@IdClass(CLOSLOId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CLOSLO {
    @Id
    @ManyToOne
    @JoinColumn(name = "slo_id")
    private SLO slo;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "lo_id")
    private LearningOutcome learningOutcome;
    
    private Float percentage;
}

