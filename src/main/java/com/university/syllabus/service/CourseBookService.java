package com.university.syllabus.service;

import com.university.syllabus.dto.CourseBookDTO;
import com.university.syllabus.model.Book;
import com.university.syllabus.model.Course;
import com.university.syllabus.model.CourseBook;
import com.university.syllabus.model.CourseBookId;
import com.university.syllabus.repository.BookRepository;
import com.university.syllabus.repository.CourseBookRepository;
import com.university.syllabus.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseBookService {
    
    private final CourseBookRepository courseBookRepository;
    private final BookRepository bookRepository;
    private final CourseRepository courseRepository;
    
    public CourseBookService(CourseBookRepository courseBookRepository, 
                            BookRepository bookRepository, 
                            CourseRepository courseRepository) {
        this.courseBookRepository = courseBookRepository;
        this.bookRepository = bookRepository;
        this.courseRepository = courseRepository;
    }
    
    public List<CourseBookDTO> getBooksForCourse(String courseId) {
        return courseBookRepository.findByCourseId(courseId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CourseBookDTO> getCoursesForBook(Long bookId) {
        return courseBookRepository.findByBookId(bookId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public CourseBookDTO addBookToCourse(Long bookId, String courseId, String type) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        
        CourseBook courseBook = new CourseBook(book, course, type);
        CourseBook savedCourseBook = courseBookRepository.save(courseBook);
        
        return convertToDTO(savedCourseBook);
    }
    
    @Transactional
    public void removeBookFromCourse(Long bookId, String courseId) {
        courseBookRepository.deleteByBookIdAndCourseId(bookId, courseId);
    }
    
    @Transactional
    public CourseBookDTO updateBookCourseRelation(Long bookId, String courseId, String type) {
        CourseBookId id = new CourseBookId(bookId, courseId);
        CourseBook courseBook = courseBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relationship not found"));
        
        courseBook.setType(type);
        CourseBook updatedCourseBook = courseBookRepository.save(courseBook);
        
        return convertToDTO(updatedCourseBook);
    }
    
    private CourseBookDTO convertToDTO(CourseBook courseBook) {
        CourseBookDTO dto = new CourseBookDTO();
        dto.setBookId(courseBook.getBook().getId());
        dto.setCourseId(courseBook.getCourse().getId());
        dto.setBookTitle(courseBook.getBook().getTitle());
        dto.setBookAuthor(courseBook.getBook().getAuthor());
        dto.setBookIsbn(courseBook.getBook().getIsbn());
        dto.setCourseName(courseBook.getCourse().getName());
        dto.setType(courseBook.getType());
        return dto;
    }
} 