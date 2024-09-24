package com.perepalacin.authors.rest.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.domains.AuthorDao;
import com.perepalacin.authors.rest.api.services.AuthorService;
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
public class AuthorControllerIntegrationTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final AuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorSuccesfullyReturnsHttp201Created() throws Exception {
        AuthorDao author = TestDataUtils.createTestAuthor();
        author.setId(null); //our post request handles creating the id for it!
        String parsedAuthor = objectMapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
                        .content(parsedAuthor) //body of the request
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccesfullyReturnsSavedAuthor() throws Exception {
        AuthorDao author = TestDataUtils.createTestAuthor();
        author.setId(null); //our post request handles creating the id for it!
        String parsedAuthor = objectMapper.writeValueAsString(author);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
                        .content(parsedAuthor) //body of the request
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Pere Palacín")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(88)
        );
    }

    @Test
    public void testThatFindAllAuthorsReturnStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFindAllAuthorReturnsAuthorsList() throws Exception {
        AuthorDao author = TestDataUtils.createTestAuthor();
        authorService.createAuthor(author);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Pere Palacín")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(88)
        );
    }

    @Test
    public void testThatGetAuthorReturnStatus200WhenAuthorExists() throws Exception {
        AuthorDao author = TestDataUtils.createTestAuthor();
        authorService.createAuthor(author);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnStatus404WhenAuthorDoesntExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorReturnAuthorWhenAuthorExists() throws Exception {
        AuthorDao author = TestDataUtils.createTestAuthor();
        authorService.createAuthor(author);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1") //api endpoint
                        .contentType(MediaType.APPLICATION_JSON) //content type = json
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Pere Palacín")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(88)
        );
    }
}
