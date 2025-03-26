package com.university.syllabus.service;

import com.university.syllabus.dto.BookDTO;
import com.university.syllabus.dto.CourseBookDTO;
import com.university.syllabus.model.Book;
import com.university.syllabus.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CourseBookService courseBookService;

    @Autowired
    public BookService(BookRepository bookRepository, CourseBookService courseBookService) {
        this.bookRepository = bookRepository;
        this.courseBookService = courseBookService;
    }

    public List<BookDTO> getAllBooks() {
        List<BookDTO> books = bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        
        // Enhance DTOs with course information
        books.forEach(book -> {
            List<CourseBookDTO> courses = courseBookService.getCoursesForBook(book.getId());
            book.setCourses(courses);
        });
        
        return books;
    }

    public Optional<BookDTO> getBookById(Long id) {
        Optional<BookDTO> bookDTO = bookRepository.findById(id)
                .map(this::convertToDTO);
        
        // Add course information if book exists
        bookDTO.ifPresent(book -> {
            List<CourseBookDTO> courses = courseBookService.getCoursesForBook(book.getId());
            book.setCourses(courses);
        });
        
        return bookDTO;
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        
        BeanUtils.copyProperties(bookDTO, existingBook, "id", "courseBooks");
        Book updatedBook = bookRepository.save(existingBook);
        
        BookDTO updatedDTO = convertToDTO(updatedBook);
        List<CourseBookDTO> courses = courseBookService.getCoursesForBook(updatedDTO.getId());
        updatedDTO.setCourses(courses);
        
        return updatedDTO;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public void addBookToCourse(Long bookId, String courseId, String type) {
        courseBookService.addBookToCourse(bookId, courseId, type);
    }
    
    public void removeBookFromCourse(Long bookId, String courseId) {
        courseBookService.removeBookFromCourse(bookId, courseId);
    }
    
    public void updateBookCourseRelation(Long bookId, String courseId, String type) {
        courseBookService.updateBookCourseRelation(bookId, courseId, type);
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        BeanUtils.copyProperties(book, dto);
        return dto;
    }

    private Book convertToEntity(BookDTO dto) {
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);
        return book;
    }
} 