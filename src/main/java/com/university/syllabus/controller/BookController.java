package com.university.syllabus.controller;

import com.university.syllabus.dto.BookDTO;
import com.university.syllabus.service.BookService;
import com.university.syllabus.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final CourseService courseService;

    @Autowired
    public BookController(BookService bookService, CourseService courseService) {
        this.bookService = bookService;
        this.courseService = courseService;
    }

    @GetMapping
    public String listBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            @RequestParam(required = false) String courseId,
            @RequestParam(required = false) Integer year,
            Model model) {
        // Define available page sizes
        int[] pageSizes = {10, 25, 50, 100};
        model.addAttribute("pageSizes", pageSizes);
        
        // Get all courses for filter dropdown
        model.addAttribute("courses", courseService.getAllCourses());
        
        // Add filter values to model
        model.addAttribute("selectedCourseId", courseId);
        model.addAttribute("selectedYear", year);
        
        Page<BookDTO> bookPage = bookService.getAllBooks(PageRequest.of(page, size), courseId, year);
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("totalItems", bookPage.getTotalElements());
        model.addAttribute("pageSize", size);
        
        // Calculate start and end indices
        long startIndex = page * size + 1;
        long endIndex = Math.min((page + 1) * size, bookPage.getTotalElements());
        model.addAttribute("startIndex", startIndex);
        model.addAttribute("endIndex", endIndex);
        
        return "book/list";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        Optional<BookDTO> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "book/view";
        }
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "book/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<BookDTO> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "book/form";
        }
        return "redirect:/books";
    }

    @GetMapping("/{id}/courses")
    public String viewBookCourses(@PathVariable Long id, Model model) {
        return "redirect:/course-books/book/" + id;
    }

    @PostMapping
    public String createBook(@ModelAttribute BookDTO book, RedirectAttributes redirectAttributes) {
        try {
            bookService.createBook(book);
            redirectAttributes.addFlashAttribute("success", "Book created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating book: " + e.getMessage());
        }
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute BookDTO book, RedirectAttributes redirectAttributes) {
        try {
            bookService.updateBook(id, book);
            redirectAttributes.addFlashAttribute("success", "Book updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating book: " + e.getMessage());
        }
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("success", "Book deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting book: " + e.getMessage());
        }
        return "redirect:/books";
    }
} 