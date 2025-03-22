package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "pathway")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pathway {
    @Id
    private Integer id;
    
    @Column(nullable = false)
    private String pathway;
} 