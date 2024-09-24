package com.perepalacin.authors.rest.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.domains.BookDao;
import com.perepalacin.authors.rest.api.domains.dto.BookDto;
import com.perepalacin.authors.rest.api.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final BookService bookService;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBookSuccesfullyReturnsHttp201Created() throws Exception {
        BookDao book = TestDataUtils.createTestBook(null);
        String parsedBook = objectMapper.writeValueAsString(book);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + book.getIsbn()) //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
                        .content(parsedBook) //body of the request
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookSuccesfullyReturnsSavedAuthor() throws Exception {
        BookDao book = TestDataUtils.createTestBook(null);
        String parsedAuthor = objectMapper.writeValueAsString(book);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + book.getIsbn()) //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
                        .content(parsedAuthor) //body of the request
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Jonny and the Stone")
        );
    }

    @Test
    public void testThatFindAllBooksReturnStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFindAllBooksReturnsBooksList() throws Exception {
        BookDao book = TestDataUtils.createTestBook(null);
        bookService.createBook(book.getIsbn(), book);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value(book.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(book.getTitle())
        );
    }


    @Test
    public void testThatGetBookReturnStatus200WhenBookExists() throws Exception {
        BookDao book = TestDataUtils.createTestBook(null);
        book.setIsbn("978-1-2345-6789-0");
        bookService.createBook("978-1-2345-6789-0", book);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/978-1-2345-6789-0") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetBookReturnStatus404WhenBookDoesntExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/978-1-2345-6789-0") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetBookReturnBookWhenBookExists() throws Exception {
        BookDao book = TestDataUtils.createTestBook(null);
        book.setIsbn("978-1-2345-6789-0");
        bookService.createBook("978-1-2345-6789-0", book);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/978-1-2345-6789-0") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("978-1-2345-6789-0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle())
        );
    }
}
