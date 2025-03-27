package com.university.syllabus.controller;

import com.university.syllabus.dto.CourseBookDTO;
import com.university.syllabus.service.BookService;
import com.university.syllabus.service.CourseBookService;
import com.university.syllabus.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/course-books")
public class CourseBookController {
    
    private final CourseBookService courseBookService;
    private final BookService bookService;
    private final CourseService courseService;
    
    @Autowired
    public CourseBookController(CourseBookService courseBookService, 
                              BookService bookService,
                              CourseService courseService) {
        this.courseBookService = courseBookService;
        this.bookService = bookService;
        this.courseService = courseService;
    }
    
    @GetMapping("/course/{courseId}")
    public String getBooksForCourse(@PathVariable String courseId, Model model) {
        List<CourseBookDTO> books = courseBookService.getBooksForCourse(courseId);
        model.addAttribute("courseBooks", books);
        model.addAttribute("course", courseService.getCourseById(courseId).orElse(null));
        model.addAttribute("allBooks", bookService.getAllBooks());
        return "course-book/course-books";
    }
    
    @GetMapping("/book/{bookId}")
    public String getCoursesForBook(@PathVariable Long bookId, Model model) {
        List<CourseBookDTO> courses = courseBookService.getCoursesForBook(bookId);
        model.addAttribute("courseBooks", courses);
        model.addAttribute("book", bookService.getBookById(bookId).orElse(null));
        model.addAttribute("allCourses", courseService.getAllCourses());
        return "course-book/book-courses";
    }
    
    @PostMapping("/add")
    public String addBookToCourse(@RequestParam Long bookId, 
                                @RequestParam String courseId,
                                @RequestParam String type,
                                RedirectAttributes redirectAttributes) {
        try {
            bookService.addBookToCourse(bookId, courseId, type);
            redirectAttributes.addFlashAttribute("success", "Book added to course successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding book to course: " + e.getMessage());
        }
        return "redirect:/course-books/course/" + courseId;
    }
    
    @PostMapping("/remove")
    public String removeBookFromCourse(@RequestParam Long bookId, 
                                     @RequestParam String courseId,
                                     @RequestParam(required = false) String returnTo,
                                     RedirectAttributes redirectAttributes) {
        try {
            bookService.removeBookFromCourse(bookId, courseId);
            redirectAttributes.addFlashAttribute("success", "Book removed from course successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error removing book from course: " + e.getMessage());
        }
        
        if ("book".equals(returnTo)) {
            return "redirect:/course-books/book/" + bookId;
        } else {
            return "redirect:/course-books/course/" + courseId;
        }
    }
    
    @PostMapping("/update")
    public String updateBookCourseRelation(@RequestParam Long bookId,
                                         @RequestParam String courseId,
                                         @RequestParam String type,
                                         @RequestParam(required = false) String returnTo,
                                         RedirectAttributes redirectAttributes) {
        try {
            bookService.updateBookCourseRelation(bookId, courseId, type);
            redirectAttributes.addFlashAttribute("success", "Book-course relationship updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating book-course relationship: " + e.getMessage());
        }
        
        if ("book".equals(returnTo)) {
            return "redirect:/course-books/book/" + bookId;
        } else {
            return "redirect:/course-books/course/" + courseId;
        }
    }
} 