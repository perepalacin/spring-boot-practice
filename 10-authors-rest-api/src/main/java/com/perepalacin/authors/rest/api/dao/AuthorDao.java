package com.perepalacin.authors.rest.api.dao;

import com.perepalacin.authors.rest.api.domains.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findById(long authorId);

    List<Author> findAll();

    void update(Long id, Author author);

    void delete(Long id);
}
