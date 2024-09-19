package com.perepalacin.authors.rest.api.dao.impls;
import com.perepalacin.authors.rest.api.TestDataUtils;
import com.perepalacin.authors.rest.api.domains.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    //    Mokit is an extensions that helps us create unit test by creating mocks of our singleton beans upon injection
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSQL() {
        Book book = TestDataUtils.createTestBook();

        underTest.create(book);

        // Mockito.verify verifies that a certain method is called
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("978-1-2345-6789-0"), eq("Jonny and the Stone"), eq(1L)
        );
    }

    @Test
    public void testThatFindByIdGeneratesTheCorrectSQL() {
        underTest.findByIsbn("978-1-2345-6789-0");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq("978-1-2345-6789-0")
        );
    }
}
