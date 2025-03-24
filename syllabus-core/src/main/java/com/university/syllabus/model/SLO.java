// SLO.java (Student Learning Outcome)
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "slo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SLO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String description;
    
    private Integer criteria;
}
