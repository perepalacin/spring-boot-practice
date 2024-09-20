package com.perepalacin.authors.rest.api.dao.impls;

import com.perepalacin.authors.rest.api.dao.BookDao;
import com.perepalacin.authors.rest.api.domains.Author;
import com.perepalacin.authors.rest.api.domains.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(), book.getTitle(), book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(), isbn);

        return results.stream().findFirst(); //Find first is a method that returns an optional already
    }

    public static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BookDaoImpl.BookRowMapper()
        );
    }

}
