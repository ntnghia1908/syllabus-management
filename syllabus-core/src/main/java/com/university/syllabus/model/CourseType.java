package com.university.syllabus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "course_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseType {
    @Id
    private Integer id;
    
    private String type;
    
    @Column(name = "type_vn")
    private String typeVn;
}