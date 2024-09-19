package com.perepalacin.authors.rest.api.dao.impls;

import com.perepalacin.authors.rest.api.dao.AuthorDao;
import com.perepalacin.authors.rest.api.domains.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(), author.getName(), author.getAge()
        );
    }

    @Override
    public Optional<Author> findById(long authorId) {
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id = ? LIMIT 1",
                new AuthorRowMapper(), authorId);

        return results.stream().findFirst(); //Find first is a method that returns an optional already
    }

    public static class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
