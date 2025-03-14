package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "major")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;
    
    @Column(name = "full_name")
    private String fullName;
    
    @Column(name = "short_name")
    private String shortName;
    
    @OneToMany(mappedBy = "major")
    @ToString.Exclude
    private Set<Program> programs = new HashSet<>();
}