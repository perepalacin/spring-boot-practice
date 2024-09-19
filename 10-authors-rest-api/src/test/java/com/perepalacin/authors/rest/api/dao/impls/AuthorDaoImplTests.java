package com.perepalacin.authors.rest.api.dao.impls;

import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.domains.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

//    Mokit is an extensions that helps us create unit test by creating mocks of our singleton beans upon injection
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSQL() {

        Author author = TestDataUtils.createTestAuthor();

        underTest.create(author);

        // Mockito.verify verifies that a certain method is called
        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Pere Palacín"), eq(88)
                );

    }

    @Test
    public void testThatFindByIdGeneratesTheCorrectSQL() {
        underTest.findById(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }
}
