// InstructorRepository.java
package com.university.syllabus.repository;

import com.university.syllabus.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, String> {
    @Query("SELECT i FROM Instructor i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(i.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Instructor> searchInstructors(@Param("query") String query);
}
