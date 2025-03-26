package com.university.syllabus.controller;

import com.university.syllabus.dto.BookDTO;
import com.university.syllabus.service.BookService;
import com.university.syllabus.model.Book;
import com.university.syllabus.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setPublisher("Test Publisher");
        book.setYear(2024);

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Test Book");
        bookDTO.setAuthor("Test Author");
        bookDTO.setIsbn("1234567890");
        bookDTO.setPublisher("Test Publisher");
        bookDTO.setYear(2024);
    }

    @Test
    void listBooks_ShouldReturnBooksList() throws Exception {
        when(bookService.getAllBooks()).thenReturn(java.util.Arrays.asList(bookDTO));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/list"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    void viewBook_WhenBookExists_ShouldReturnBookView() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(Optional.of(bookDTO));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/view"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void showCreateForm_ShouldReturnCreateForm() throws Exception {
        mockMvc.perform(get("/books/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/form"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void showEditForm_WhenBookExists_ShouldReturnEditForm() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(Optional.of(bookDTO));

        mockMvc.perform(get("/books/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/form"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void createBook_ShouldCreateBookAndRedirect() throws Exception {
        when(bookService.createBook(any())).thenReturn(bookDTO);

        mockMvc.perform(post("/books")
                .param("title", "Test Book")
                .param("author", "Test Author")
                .param("isbn", "1234567890")
                .param("publisher", "Test Publisher")
                .param("year", "2024"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("success"));
    }

    @Test
    void updateBook_ShouldUpdateBookAndRedirect() throws Exception {
        when(bookService.updateBook(any(), any())).thenReturn(bookDTO);

        mockMvc.perform(post("/books/1")
                .param("title", "Updated Book")
                .param("author", "Updated Author")
                .param("isbn", "0987654321")
                .param("publisher", "Updated Publisher")
                .param("year", "2025"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("success"));
    }

    @Test
    void deleteBook_ShouldDeleteBookAndRedirect() throws Exception {
        mockMvc.perform(post("/books/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("success"));
    }
} 