// Program.java
package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "program")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    @Id
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;
    
    private String name;
    
    private Integer duration;
    
    private String version;
    
    private String type;
}
