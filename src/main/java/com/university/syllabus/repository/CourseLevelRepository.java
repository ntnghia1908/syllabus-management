// CourseLevelRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.CourseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLevelRepository extends JpaRepository<CourseLevel, Integer> {
}
