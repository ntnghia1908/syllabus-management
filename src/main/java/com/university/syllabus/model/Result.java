// Result.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "result")
@IdClass(ResultId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassSession classSession;
    
    @Column(name = "mid_score")
    private Integer midScore;
    
    @Column(name = "final_score")
    private Integer finalScore;
    
    @Column(name = "in_class_score")
    private Integer inClassScore;
    
    private Integer GPA;
    
    @Column(name = "abet_score")
    private Integer abetScore;
    
    @Column(name = "abet_1")
    private Integer abet1;
    
    @Column(name = "abet_2")
    private Integer abet2;
    
    @Column(name = "abet_3")
    private Integer abet3;
    
    @Column(name = "abet_4")
    private Integer abet4;
    
    @Column(name = "abet_5")
    private Integer abet5;
    
    @Column(name = "abet_6")
    private Integer abet6;
    
    private Float avg;
}
